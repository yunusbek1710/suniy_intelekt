package suniyIntelekt.services

import cats.effect.Sync
import district.domain.{ContactForm, Person, PersonForm}
import org.typelevel.log4cats.Logger
import suniyIntelekt.db.algebras.UserAlgebra
import suniyIntelekt.domain.{User, UserData}

trait UserService[F[_]] {
  def create(userData: UserData): F[User]
  def createContact(form: ContactForm): F[Unit]
  def personInfos: F[List[Person]]
  def createPerson(form: PersonForm): F[Unit]
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

  override def personInfos: F[List[Person]] =
    userAlgebra.personInfos

  override def create(userData: UserData): F[User] =
    userAlgebra.create(userData)

  override def createPerson(form: PersonForm): F[Unit] =
    userAlgebra.createPerson(form)

  override def createContact(form: ContactForm): F[Unit] =
    userAlgebra.createContact(form)

}
