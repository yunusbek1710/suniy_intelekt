package suniyIntelekt.test.utils

de ./IdeaProjects/.test.utils

import suniyIntelekt.domain.{Credentials, User, UserData}Ide ./IdeaProjects/.domain._
import /Ide ./IdeaProjects/.domain.custom.refinements._
import eu.timepit.refined.auto.autoUnwrap
import tsec.cipher.symmetric.jca.{AES128GCM, SecretKey}

import java.time.LocalDateTime
import java.util.UUID
import scala.util.Random

object FakeData {
  def randomString(length: Int): String = Random.alphanumeric.take(length).mkString

  val TsecKey: SecretKey[AES128GCM] = AES128GCM.unsafeGenerateKey

  def randomEmail: EmailAddress = EmailAddress.unsafeFrom(s"${randomString(8)}@gmail.com")

  val Pass: Password = Password.unsafeFrom("Secret1!")

  def credentials(isCorrect: Boolean): Credentials =
    if (isCorrect)
      Credentials(EmailAddress.unsafeFrom("test@test.test") , Password.unsafeFrom("Secret1!"))
    else
      Credentials(EmailAddress.unsafeFrom(FakeData.randomEmail) , Password.unsafeFrom("Secret1!"))

  def user(email: EmailAddress = randomEmail): User =
    User(
      id = UUID.randomUUID(),
      fullName = FullName.unsafeFrom("John Dao"),
      email = EmailAddress.unsafeFrom(email),
      createdAt = LocalDateTime.now
    )

  def userData: UserData =
    UserData(
      fullName = FullName.unsafeFrom("John Dao"),
      email = randomEmail,
      password = Password.unsafeFrom("Secret1!")
    )
}