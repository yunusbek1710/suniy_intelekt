package suniyIntelekt.db.sql


import eu.timepit.refined.types.string.NonEmptyString
import suniyIntelekt.implicits.PasswordOps
import skunk._
import skunk.codec.all._
import skunk.data.Type
import skunk.implicits._
import district.domain.Role
import suniyIntelekt.domain.{User, UserData}
import suniyIntelekt.domain.custom.refinements.EmailAddress

import java.util.UUID

object UserSql {

  val role: Codec[Role] = `enum`[Role](_.value, Role.find, Type("role"))
  val emailCodec: Codec[EmailAddress] = varchar.imap(email => EmailAddress.unsafeFrom(email))(email => email.value)

  val dec: Decoder[User] = (uuid ~ role ~ varchar ~ emailCodec ~ varchar).map {
  case id ~ role ~ fullName ~ email ~ _ =>
      User(id, role, NonEmptyString.unsafeFrom(fullName), email)
  }

  val enc: Encoder[UUID ~ UserData] = (uuid ~ role ~ varchar ~ emailCodec ~ varchar).contramap { case id ~ u =>
    id ~ Role.USER ~ u.fullName.value ~ u.email ~ u.password.toHashUnsafe
  }

  val insert: Query[UUID ~ UserData, User] =
    sql"""INSERT INTO users VALUES ($enc) RETURNING *""".query(dec)

  val selectByEmail: Query[EmailAddress, User] =
    sql"""SELECT * FROM users WHERE email = $emailCodec """.query(dec)

  val selectPass: Query[EmailAddress, String] =
    sql"""SELECT password_hash FROM users WHERE email = $emailCodec """.query(varchar)

}
