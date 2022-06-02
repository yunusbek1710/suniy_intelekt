package district.api

object Urls {
  type Url = String

  object Common {
    val logout:Url = "/user/logout"
    val index: Url = ""
  }

  object UserData {
    val login: Url = "/login"
  }

  object AdminData {
  }

}
