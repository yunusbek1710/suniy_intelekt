package domain

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class SignUpParams(
  name: String = "",
  email: String = "",
  password: String = "",
  confirmPassword: String = ""
)

object SignUpParams {
  implicit val enc: Encoder[SignUpParams] = deriveEncoder
  implicit val dec: Decoder[SignUpParams] = deriveDecoder
}
