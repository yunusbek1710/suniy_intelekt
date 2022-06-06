package district.domain

import cats.Show
import io.circe.{Decoder, Encoder}

sealed abstract class DocumentType(val value: String)

object DocumentType {
  case object PASSPORT extends DocumentType("passport")
  case object BIRTHCERTIFICATE  extends DocumentType("birthCertificate")
  case object IDCARD  extends DocumentType("idCard")

  val types = List(PASSPORT, BIRTHCERTIFICATE, IDCARD)

  def find(value: String): Option[DocumentType] =
    types.find(_.value == value.toLowerCase)

  def unsafeFrom(value: String): DocumentType =
    find(value).getOrElse(throw new IllegalArgumentException(s"value doesn't match [ $value ]"))

  implicit val encStatus: Encoder[DocumentType] = Encoder.encodeString.contramap[DocumentType](_.value)
  implicit val decStatus: Decoder[DocumentType] = Decoder.decodeString.map(unsafeFrom)
  implicit val show: Show[DocumentType]         = Show.show(_.value)
}


