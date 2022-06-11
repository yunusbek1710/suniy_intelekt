package suniyIntelekt.db.algebras

import cats.effect.{Resource, Sync}
import cats.implicits._
import district.domain.{ContactForm, Person, PersonForm, UserInfo}
import district.refinements.EmailAddress
import suniyIntelekt.db.sql.UserSql.{insert, insertContact, insertPerson, select, selectByEmail, selectInfo, selectPass}
import suniyIntelekt.domain.{User, UserData}
import suniyIntelekt.utils.GenUUID
import skunk._
import skunk.implicits.toIdOps
import tsec.passwordhashers.PasswordHash
import tsec.passwordhashers.jca.SCrypt


trait UserAlgebra[F[_]] extends IdentityProvider[F, UserInfo] {
  def findByEmail(email: EmailAddress): F[Option[UserInfo]]
  def retrievePass(email: EmailAddress): F[Option[PasswordHash[SCrypt]]]
  def create(user: UserData): F[User]
  def personInfos: F[List[Person]]
  def createPerson(form: PersonForm): F[Unit]
  def createContact(form: ContactForm): F[Unit]

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
        println(form)
        prepCmd(insertPerson, uuid ~ form)
      }

    override def createContact(form: ContactForm): F[Unit] =
      GenUUID[F].make.flatMap { uuid =>
        prepCmd(insertContact, uuid ~ form)
      }

    override def personInfos: F[List[Person]] =
      prepListQuery(selectInfo, Void)

  }
}
