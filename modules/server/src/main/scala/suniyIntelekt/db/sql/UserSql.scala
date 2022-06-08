package suniyIntelekt.db.sql

import eu.timepit.refined.types.string.NonEmptyString
import suniyIntelekt.implicits.PasswordOps
import skunk._
import skunk.codec.all._
import skunk.data.Type
import skunk.implicits._
import district.domain.{DocumentType, Gender, PersonForm, Role, UserInfo}
import district.refinements.EmailAddress
import suniyIntelekt.domain.{User, UserData}

import java.util.UUID

object UserSql {

  val role: Codec[Role] = `enum`[Role](_.value, Role.find, Type("role"))
  val gender: Codec[Gender] = `enum`[Gender](_.value, Gender.find, Type("gender"))
  val documentType: Codec[DocumentType] = `enum`[DocumentType](_.value, DocumentType.find, Type("type"))
  val emailCodec: Codec[EmailAddress] = varchar.imap(email => EmailAddress.unsafeFrom(email))(email => email.value)

  val dec: Decoder[User] = (uuid ~ role ~ varchar ~ emailCodec ~ varchar).map {
  case id ~ role ~ fullName ~ email ~ _ =>
      User(id, role, NonEmptyString.unsafeFrom(fullName), email)
  }

  val enc: Encoder[UUID ~ UserData] = (uuid ~ role ~ varchar ~ emailCodec ~ varchar).contramap { case id ~ u =>
    id ~ Role.USER ~ u.fullName.value ~ u.email ~ u.password.toHashUnsafe
  }

  val encPerson: Encoder[UUID ~ PersonForm] = (uuid ~ documentType ~ varchar ~ varchar ~ varchar ~ varchar ~ varchar ~ varchar.opt ~ gender ~ varchar ~ int4 ~ varchar ~ varchar ~ varchar ~ varchar ~ varchar ~ varchar ~ varchar).contramap { case id ~ p =>
    id ~ p.documentType ~ p.documentNumber.value ~ p.firstName.value ~ p.lastName.value ~ p.fathersName.value ~ p.livingPlace.value ~ p.phoneNumber.map(_.value) ~ p.gender ~ p.street.value ~ p.houseNumber ~ p.employmentStatus.value ~ p.educationalInfo.value ~ p.familyStatus.value ~ p.healthStatus.value ~ p.youthNote.value ~ p.ironNote.value ~ p.womenNote.value
  }

  val decUserInfo: Decoder[UserInfo] = (uuid ~ role ~ varchar ~ varchar).map {
    case id ~ role ~ name ~ email =>
      UserInfo(
        id = id,
        role = role,
        name = NonEmptyString.unsafeFrom(name),
        email = EmailAddress.unsafeFrom(email)
      )
  }

  val insert: Query[UUID ~ UserData, User] =
    sql"""INSERT INTO users VALUES ($enc) RETURNING *""".query(dec)

  val insertPerson: Command[UUID ~ PersonForm] =
    sql"""INSERT INTO personal_data VALUES ($encPerson)""".command

  val selectByEmail: Query[EmailAddress, UserInfo] =
    sql"""SELECT id, role, name, email FROM users WHERE email = $emailCodec """.query(decUserInfo)

  val select: Query[UUID, User] =
    sql"""SELECT * FROM users WHERE id = $uuid """.query(dec)

  val selectPass: Query[EmailAddress, String] =
    sql"""SELECT password FROM users WHERE email = $emailCodec """.query(varchar)

}
