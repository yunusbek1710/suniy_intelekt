package district.domain

import eu.timepit.refined.types.string.NonEmptyString
import io.circe.refined._
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

import java.util.UUID

case class UserInfo(
  id: UUID,
  role: Role,
  name: NonEmptyString
)

object UserInfo {
  implicit val dec: Decoder[UserInfo] = deriveDecoder[UserInfo]
  implicit val enc: Encoder[UserInfo] = deriveEncoder[UserInfo]
}