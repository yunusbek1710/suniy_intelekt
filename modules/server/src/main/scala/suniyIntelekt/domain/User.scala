package suniyIntelekt.domain

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import io.circe.refined._
import suniyIntelekt.domain.custom.refinements.{EmailAddress, FullName}

import java.time.LocalDateTime
import java.util.UUID

case class User(
  id: UUID,
  fullName: FullName,
  email: EmailAddress,
  createdAt: LocalDateTime
)

object User {
  implicit val enc: Encoder[User] = deriveEncoder[User]
  implicit val dec: Decoder[User] = deriveDecoder[User]
}
