package suniyIntelekt.domain

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.refined._
import io.circe.{Decoder, Encoder}
import suniyIntelekt.domain.custom.refinements.{EmailAddress, FullName, Password}

case class UserData(
  fullName: FullName,
  email: EmailAddress,
  password: Password
)

object UserData {
  implicit val enc: Decoder[UserData] = deriveDecoder[UserData]
  implicit val dec: Encoder[UserData] = deriveEncoder[UserData]
}
