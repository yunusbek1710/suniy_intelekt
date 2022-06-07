package district.utils

object StringUtils {
  implicit class StringOps(val str: String) {
    def toOption: Option[String] = if (str.trim.isEmpty) None else Some(str)

    def when__(cond: => Boolean): Option[String] = if (cond) Some(str) else None
  }
}