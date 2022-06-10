package district.domain

import eu.timepit.refined.types.string.NonEmptyString
import io.circe.{Decoder, Encoder}
import io.circe.refined._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

import java.util.UUID

case class Person(
  id: UUID,
  documentType: DocumentType,
  documentNumber: NonEmptyString,
  birthday: NonEmptyString,
  firstName: NonEmptyString,
  lastName: NonEmptyString,
  fathersName: NonEmptyString,
  livingPlace: NonEmptyString,
  phoneNumber: Option[String],
  gender: Gender,
  street: NonEmptyString,
  houseNumber: Int,
  employmentStatus: NonEmptyString,
  educationalInfo: NonEmptyString,
  familyStatus: NonEmptyString,
  healthStatus: NonEmptyString,
  youthNote: NonEmptyString,
  ironNote: NonEmptyString,
  womenNote: NonEmptyString
)

object Person {
  implicit val dec: Decoder[Person] = deriveDecoder[Person]
  implicit val enc: Encoder[Person] = deriveEncoder[Person]
}
