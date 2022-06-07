package district.utils

import cats.data._

object MapConvert {
  type ValidationResult[A] = ValidatedNec[String, A]
}

trait MapConvert[F[_], A] {
  def fromMap(values: Map[String, String]): F[A]
}
