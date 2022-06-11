package domain

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class ContactUsParams(
  firstName: String = "",
  lastName: String = "",
  phone: String = "",
  description: String = ""
)

object ContactUsParams {
  implicit val dec: Decoder[ContactUsParams] = deriveDecoder[ContactUsParams]
  implicit val enc: Encoder[ContactUsParams] = deriveEncoder[ContactUsParams]
}

