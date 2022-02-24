package suniyIntelekt

import cats.implicits._
import cats.effect.{Async, Sync}
import suniyIntelekt.domain.custom.exception.MultipartDecodeError
import suniyIntelekt.domain.custom.refinements.Password
import suniyIntelekt.domain.custom.utils.MapConvert
import suniyIntelekt.domain.custom.utils.MapConvert.ValidationResult
import eu.timepit.refined.auto.autoUnwrap
import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, Printer}
import org.http4s.MediaType
import org.http4s.headers.`Content-Type`
import org.http4s.multipart.Part
import tsec.passwordhashers.PasswordHash
import tsec.passwordhashers.jca.SCrypt

package object implicits {

  implicit class PasswordOps(val password: Password) {
    def toHash[F[_]: Sync]: F[PasswordHash[SCrypt]] = SCrypt.hashpw[F](password)

    def toHashUnsafe: PasswordHash[SCrypt] = SCrypt.hashpwUnsafe(password)
  }

  implicit class PartOps[F[_]: Async](parts: Vector[Part[F]]) {
    private def filterFileTypes(part: Part[F]): Boolean = part.filename.isDefined

    def fileParts: Vector[Part[F]] = parts.filter(filterFileTypes)

    def fileParts(mediaType: MediaType): Vector[Part[F]] =
      parts.filter(_.headers.get[`Content-Type`].exists(_.mediaType == mediaType))

    def isFilePartExists: Boolean = parts.exists(filterFileTypes)

    def textParts: Vector[Part[F]] = parts.filterNot(filterFileTypes)

    def convert[A](implicit mapper: MapConvert[F, ValidationResult[A]], F: Sync[F]): F[A] =
      for {
        collectKV <- textParts.traverse { part =>
          part.bodyText.compile.foldMonoid.map(part.name.get -> _)
        }
        entity <- mapper.fromMap(collectKV.toMap)
        validEntity <- entity.fold(
          error => {
            F.raiseError[A](MultipartDecodeError(error.toList.mkString(" | ")))
          },
          success => success.pure[F]
        )
      } yield validEntity
  }

  implicit class CirceDecoderOps(s: String) {
    def as[A: Decoder]: A = decode[A](s).fold(throw _, json => json)
  }

  implicit class GenericTypeOps[A](obj: A) {
    private val printer: Printer = Printer.noSpaces.copy(dropNullValues = true)

    def toOptWhen(cond: => Boolean): Option[A] = if (cond) Some(obj) else None

    def toJson(implicit encoder: Encoder[A]): String = obj.asJson.printWith(printer)
  }

}
