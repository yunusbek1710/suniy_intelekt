package suniyIntelekt.modules

import cats.implicits._
import cats.effect.{Async, Sync}
import suniyIntelekt.db.algebras.{IdentityProvider, UserAlgebra}
import suniyIntelekt.domain.User
import suniyIntelekt.security.AuthService
import suniyIntelekt.services.LiveIdentityService
import suniyIntelekt.services.redis.RedisClient

object Authentication {
  private[this] def makeAuthService[F[_]: Async: Sync, U](
    identityProvider: IdentityProvider[F, U]
  )(implicit redisClient: RedisClient[F]): F[AuthService[F, U]] =
    for {
      identityService <- LiveIdentityService[F, U](identityProvider)
      key <- redisClient.secretKeyStore.getSecretKey
      authService <- AuthService[F, U](identityService, key)
    } yield authService

  def apply[F[_]: Async](
    userProvider: UserAlgebra[F]
  )(implicit F: Sync[F], redisClient: RedisClient[F]): F[Authentication[F]] =
    makeAuthService[F, User](userProvider).map { userAuth =>
      new Authentication[F](userAuth)
    }
}

final class Authentication[F[_]] private (
  val user: AuthService[F, User]
)
