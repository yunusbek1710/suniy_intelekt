package suniyIntelekt.db.algebras

import cats.effect.{Resource, Sync}
import cats.implicits._
import district.domain.{PersonForm, UserInfo}
import district.refinements.EmailAddress
import suniyIntelekt.db.sql.UserSql.{insert, insertPerson, select, selectByEmail, selectPass}
import suniyIntelekt.domain.{User, UserData}
import suniyIntelekt.utils.GenUUID
import eu.timepit.refined.auto.autoUnwrap
import skunk.Session
import skunk.implicits.toIdOps
import tsec.passwordhashers.PasswordHash
import tsec.passwordhashers.jca.SCrypt

import java.util.UUID

trait UserAlgebra[F[_]] extends IdentityProvider[F, UserInfo] {
  def findByEmail(email: EmailAddress): F[Option[UserInfo]]
  def retrievePass(email: EmailAddress): F[Option[PasswordHash[SCrypt]]]
  def create(user: UserData): F[User]
  def get(email: EmailAddress): F[Option[UserInfo]]
  def createPerson(form: PersonForm): F[Unit]

}

object UserAlgebra {
  def apply[F[_]](implicit F: Sync[F], session: Resource[F, Session[F]]): F[UserAlgebra[F]] =
    F.delay(
      new LiveUserAlgebra[F]
    )

  final class LiveUserAlgebra[F[_]](implicit F: Sync[F], session: Resource[F, Session[F]])
      extends SkunkHelper[F]
      with UserAlgebra[F] {

    override def findByEmail(email: EmailAddress): F[Option[UserInfo]] = prepOptQuery(selectByEmail, email)

    override def retrievePass(email: EmailAddress): F[Option[PasswordHash[SCrypt]]] =
      prepOptQuery(selectPass, email).map(_.map(PasswordHash[SCrypt]))

    override def create(userData: UserData): F[User] =
      GenUUID[F].make.flatMap { uuid =>
        prepQueryUnique(insert, uuid ~ userData)
      }

    override def createPerson(form: PersonForm): F[Unit] =
      GenUUID[F].make.flatMap { uuid =>
        prepCmd(insertPerson, uuid ~ form)
      }

    override def get(email: EmailAddress): F[Option[UserInfo]] =
      prepOptQuery(selectByEmail, email)

  }
}
