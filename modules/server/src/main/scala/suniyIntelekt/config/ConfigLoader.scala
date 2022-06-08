package suniyIntelekt.config

import cats.effect.Async
import cats.implicits._
import ciris._
import ciris.refined._
import district.refinements.UriAddress
import eu.timepit.refined.types.net.UserPortNumber
import eu.timepit.refined.types.numeric.PosInt
import eu.timepit.refined.types.string.NonEmptyString

object ConfigLoader {
  private[this] def databaseConfig: ConfigValue[Effect, DBConfig] = (
    env("POSTGRES_HOST").as[NonEmptyString],
    env("POSTGRES_PORT").as[UserPortNumber],
    env("POSTGRES_USER").as[NonEmptyString],
    env("POSTGRES_PASSWORD").as[NonEmptyString],
    env("POSTGRES_DATABASE").as[NonEmptyString],
    env("POSTGRES_POOL_SIZE").as[PosInt]
  ).parMapN(DBConfig.apply)

  private[this] def httpLogConfig: ConfigValue[Effect, LogConfig] = (
    env("HTTP_HEADER_LOG").as[Boolean],
    env("HTTP_BODY_LOG").as[Boolean]
  ).parMapN(LogConfig.apply)

  private[this] def httpServerConfig: ConfigValue[Effect, HttpServerConfig] = (
    env("HTTP_HOST").as[NonEmptyString],
    env("HTTP_PORT").as[UserPortNumber]
  ).parMapN(HttpServerConfig.apply)


  private[this] def redisConfig: ConfigValue[Effect, RedisConfig] = (
    env("REDIS_SERVER_URI").as[UriAddress]
    ).map(RedisConfig.apply)

  def app[F[_]: Async]: F[AppConfig] = (
    databaseConfig,
    httpLogConfig,
    httpServerConfig,
    redisConfig
  ).parMapN(AppConfig.apply).load[F]
}
