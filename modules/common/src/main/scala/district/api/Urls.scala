package district.api

object Urls {
  type Url = String

  object Common {
    val logout:Url = "/user/logout"
    val index: Url = ""
    val contact: Url = "/user/contact"
  }

  object UserData {
    val login: Url = "/user/login"
    val user: Url = "/user"
    val register: Url = "/user/register"
  }

  object AdminData {
    val add: Url = "/user/create-person"
    val infos: Url = "/user/person-infos"
  }

}
