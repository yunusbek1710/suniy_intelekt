package suniyIntelekt.modules

import cats.implicits._
import cats.effect.{Async, Sync}
import org.typelevel.log4cats.Logger
import suniyIntelekt.db.algebras.Algebras
import suniyIntelekt.services.{LiveUserService, UserService}
import suniyIntelekt.services.redis.RedisClient

object Uniy_intelektProgram {
  def apply[F[_]: Sync: Async: Logger](
    database: Database[F],
    redisClient: RedisClient[F]
  ): F[Uniy_intelektProgram[F]] = {
    implicit val redis: RedisClient[F] = redisClient

    def algebrasF: F[Algebras[F]] = (
      database.user
    ).map(Algebras.apply)

    for {
      algebras <- algebrasF
      auth <- Authentication[F](algebras.user)
      userService <- LiveUserService[F](algebras.user)
    } yield new Uniy_intelektProgram[F](auth, userService)
  }
}

final class Uniy_intelektProgram[F[_]: Sync] private (
  val auth: Authentication[F],
  val userService: UserService[F]
)
