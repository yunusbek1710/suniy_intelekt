package district

import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.{Interval, Negative}
import eu.timepit.refined.string.{MatchesRegex, Uri, Url}

package object refinements {
  private type PhonePred               = MatchesRegex["\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$"]
  private type EmailPred               = MatchesRegex["^[a-zA-Z0-9.-]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$"]
  private type PasswordPred            = MatchesRegex["^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,32}$"]
  private type FullNamePred            = MatchesRegex["^[a-zA-Z]{3,}(?:\\s[a-zA-Z]+)+$"]
  private type RatingCommentPred       = Interval.Closed[1,5] And Not[Negative]
  private type BucketPred              = MatchesRegex["^[a-z0-9/.-]{3,63}$"]
  private type FilePathPred            = MatchesRegex["[a-z0-9-]+\\.+(png|jpg|jpeg|bmp)"]
  private type FileKeyPred             = MatchesRegex["[a-z0-9-]+\\.+(png|jpg|jpeg|bmp)"]
  private type ProductRatingPred       = Interval.Closed[1, 5] And Not[Negative]


  type FullName = String Refined FullNamePred
  object FullName extends RefinedTypeOps[FullName, String]

  type EmailAddress = String Refined EmailPred
  object EmailAddress extends RefinedTypeOps[EmailAddress, String]

  type PhoneNumber = String Refined PhonePred
  object PhoneNumber extends RefinedTypeOps[PhoneNumber, String]

  type Password = String Refined (NonEmpty And PasswordPred)
  object Password extends RefinedTypeOps[Password, String]

  type UriAddress = String Refined Uri
  object UriAddress extends RefinedTypeOps[UriAddress, String]

  type ProductRating = Double Refined ProductRatingPred
  object ProductRating extends RefinedTypeOps[ProductRating, Double]

  type FilePath = String Refined (NonEmpty And FilePathPred)
  object FilePath extends RefinedTypeOps[FilePath, String]

  type FileKey = String Refined (NonEmpty And FileKeyPred)
  object FileKey extends RefinedTypeOps[FileKey, String]

  type RatingComment = Int Refined RatingCommentPred
  object RatingComment extends RefinedTypeOps[RatingComment, Int]

  type BucketName = String Refined BucketPred
  object BucketName extends RefinedTypeOps[BucketName, String]

  type Prefix = String Refined NonEmpty
  object Prefix extends RefinedTypeOps[Prefix, String]

  type UrlAddress = String Refined Url
  object UrlAddress extends RefinedTypeOps[UrlAddress, String]

  type Key = String Refined NonEmpty
  object Key extends RefinedTypeOps[Key, String]
}
