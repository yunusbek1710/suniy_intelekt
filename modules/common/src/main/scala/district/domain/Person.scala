package district.domain

import eu.timepit.refined.types.string.NonEmptyString

import java.util.UUID

case class Person(
  id: UUID,
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


