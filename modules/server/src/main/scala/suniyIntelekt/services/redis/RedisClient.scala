package suniyIntelekt.services.redis

import cats.data.OptionT
import cats.implicits._
import cats.effect.{Async, Resource, Sync}
import dev.profunktor.redis4cats.{Redis, RedisCommands}
import dev.profunktor.redis4cats.effect.Log.Stdout._
import dev.profunktor.redis4cats.effects.{SetArg, SetArgs}
import eu.timepit.refined.auto.autoUnwrap
import io.circe.Codec
import suniyIntelekt.config.RedisConfig
import suniyIntelekt.implicits.{CirceDecoderOps, GenericTypeOps}
import tsec.authentication.BackingStore

object RedisClient {
  def apply[F[_]: Async](redisConfig: RedisConfig)(implicit F: Sync[F]): F[RedisClient[F]] =
    F.delay(new RedisClient(redisConfig))
}

class RedisClient[F[_]: Async](redisConfig: RedisConfig) {
  private implicit val RedisService: Resource[F, RedisCommands[F, String, String]] = Redis[F].utf8(redisConfig.uri)

  def secretKeyStore: TsecSecretKeyStore[F] = TsecSecretKeyStore[F]

  def dummyBackingStore[I, V: Codec](
    getId: V => I
  )(implicit F: Sync[F]): BackingStore[F, I, V] = new BackingStore[F, I, V] {

    override def put(elem: V): F[V] =
      RedisService.use { redis =>
        redis
          .setNx(getId(elem).toString, elem.toJson)
          .map { result =>
            if (result) elem else throw new IllegalArgumentException
          }
      }

    override def get(id: I): OptionT[F, V] =
      OptionT {
        RedisService.use { redis =>
          redis.get(id.toString).map(_.map(_.as[V]))
        }
      }

    override def update(v: V): F[V] =
      RedisService.use { redis =>
        redis
          .set(getId(v).toString, v.toJson, SetArgs(SetArg.Existence.Xx))
          .map { result =>
            if (result) v else throw new IllegalArgumentException
          }
      }

    override def delete(id: I): F[Unit] =
      RedisService.use { redis =>
        redis.del(id.toString).map { result =>
          if (result == 1) () else throw new IllegalArgumentException
        }
      }
  }
}
