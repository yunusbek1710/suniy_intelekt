package district.domain

import cats.effect.Sync
import cats.implicits._
import district.refinements.{EmailAddress, Password}
import district.utils.MapConvert
import district.utils.MapConvert.ValidationResult
import io.circe.{Decoder, Encoder}
import io.circe.refined._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class EmailAndPassword(email: EmailAddress, password: Password)

object EmailAndPassword {

  implicit def decodeMap[F[_]: Sync]: MapConvert[F, ValidationResult[EmailAndPassword]] =
    (values: Map[String, String]) =>
      (
        values
          .get("email")
          .map(EmailAddress.unsafeFrom(_).validNec)
          .getOrElse("Field [ email ] isn't defined".invalidNec),
        values
          .get("password")
          .map(Password.unsafeFrom(_).validNec)
          .getOrElse("Field [ password ] isn't defined".invalidNec)
        ).mapN(EmailAndPassword.apply).pure[F]

  implicit val dec: Decoder[EmailAndPassword] = deriveDecoder[EmailAndPassword]
  implicit val enc: Encoder[EmailAndPassword] = deriveEncoder[EmailAndPassword]
}



