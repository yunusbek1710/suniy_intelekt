package domain

import district.domain.{DocumentType, Gender}
import district.domain.DocumentType.DEFAULT
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class PersonParams(
  documentType: DocumentType = DEFAULT,
  documentNumber: String = "",
  birthday: String = "",
  firstName: String = "",
  lastName: String = "",
  fathersName: String = "",
  livingPlace: String = "",
  phoneNumber: Option[String] = None,
  gender: Gender = Gender.DEFAULT,
  street: String = "",
  houseNumber: Int = 0,
  employmentStatus: String = "",
  educationalInfo: String = "",
  familyStatus: String = "",
  healthStatus: String = "",
  youthNote: String = "",
  ironNote: String = "",
  womenNote: String = ""
)


object PersonParams {
  implicit val dec: Decoder[PersonParams] = deriveDecoder[PersonParams]
  implicit val enc: Encoder[PersonParams] = deriveEncoder[PersonParams]
}
