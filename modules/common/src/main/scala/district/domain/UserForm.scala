package district.domain

import district.refinements.{EmailAddress, Password}
import eu.timepit.refined.types.string.NonEmptyString
import io.circe.refined._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}


case class UserForm(
  fullName: NonEmptyString,
  email: EmailAddress,
  password: Password
)


object UserForm {
  implicit val userFormEnc: Decoder[UserForm] = deriveDecoder[UserForm]
  implicit val userFormDec: Encoder[UserForm] = deriveEncoder[UserForm]
}
