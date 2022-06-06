package suniyIntelekt.domain

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import district.domain.Role
import eu.timepit.refined.types.string.NonEmptyString
import io.circe.refined._
import suniyIntelekt.domain.custom.refinements.EmailAddress

import java.util.UUID

case class User(
  id: UUID,
  role: Role,
  name: NonEmptyString,
  email: EmailAddress
)

object User {
  implicit val enc: Encoder[User] = deriveEncoder[User]
  implicit val dec: Decoder[User] = deriveDecoder[User]
}
