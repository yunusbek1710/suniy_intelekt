package suniyIntelekt.services

import cats.effect.Sync
import org.typelevel.log4cats.Logger
import suniyIntelekt.db.algebras.UserAlgebra
import suniyIntelekt.domain.{User, UserData}

trait UserService[F[_]] {
  def create(userData: UserData): F[User]
}

object LiveUserService {
  def apply[F[_]: Logger](
    userAlgebra: UserAlgebra[F]
  )(implicit F: Sync[F]): F[LiveUserService[F]] =
    F.delay(
      new LiveUserService[F](userAlgebra)
    )
}

final class LiveUserService[F[_]: Logger](
  userAlgebra: UserAlgebra[F]
)(implicit F: Sync[F])
    extends UserService[F] {

  override def create(userData: UserData): F[User] =
    userAlgebra.create(userData)
}
