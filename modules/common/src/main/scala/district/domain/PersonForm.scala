package district.domain

import eu.timepit.refined.types.string.NonEmptyString
import io.circe.{Decoder, Encoder}
import io.circe.refined._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class PersonForm(
  documentType: DocumentType,
  documentNumber: NonEmptyString,
  firstName: NonEmptyString,
  lastName: NonEmptyString,
  fathersName: NonEmptyString,
  livingPlace: NonEmptyString,
  phoneNumber: Option[NonEmptyString],
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

object PersonForm {
  implicit val dec: Decoder[PersonForm] = deriveDecoder[PersonForm]
  implicit val enc: Encoder[PersonForm] = deriveEncoder[PersonForm]
}
