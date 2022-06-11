package district.domain

import eu.timepit.refined.types.string.NonEmptyString
import io.circe.{Decoder, Encoder}
import io.circe.refined._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ContactForm(
  firstName: NonEmptyString,
  lastName: NonEmptyString,
  phone: NonEmptyString,
  description: NonEmptyString
)

object ContactForm {
  implicit val userFormEnc: Decoder[ContactForm] = deriveDecoder[ContactForm]
  implicit val userFormDec: Encoder[ContactForm] = deriveEncoder[ContactForm]
}


