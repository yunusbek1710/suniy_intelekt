package suniyIntelekt.domain.custom


import eu.timepit.refined.api.{Refined, RefinedTypeOps}
import eu.timepit.refined.boolean.And
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.string.{MatchesRegex, Uri, Url}

package object refinements {
  private type EmailPred = MatchesRegex["^[a-zA-Z0-9.]+@[a-zA-Z0-9]+\\.[a-zA-Z]+$"]
  private type PasswordPred = MatchesRegex["^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,32}$"]
  private type FullNamePred = MatchesRegex["^[a-zA-Z]{3,}(?:\\s[a-zA-Z]+)+$"]

  type FullName = String Refined FullNamePred
  object FullName extends RefinedTypeOps[FullName, String]

  type EmailAddress = String Refined EmailPred
  object EmailAddress extends RefinedTypeOps[EmailAddress, String]

  type Password = String Refined (NonEmpty And PasswordPred)
  object Password extends RefinedTypeOps[Password, String]

  type UrlAddress = String Refined Url
  object UrlAddress extends RefinedTypeOps[UrlAddress, String]

  type UriAddress = String Refined Uri
  object UriAddress extends RefinedTypeOps[UriAddress, String]

}
