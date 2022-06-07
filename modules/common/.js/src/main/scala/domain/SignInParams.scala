package domain

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

case class SignInParams(
  email: String = "",
  password: String = ""
)

object SignInParams {
  implicit val enc: Encoder[SignInParams] = deriveEncoder
}
