package mahalla

import TopLevelComponent.AppPage.{AboutUsPage, AddPersonPage, IndexPage, NewsPage, PersonInfoPage, ServicesPage}
import district.domain.UserInfo
import japgolly.scalajs.react.React.Context
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.{BackendScope, Component, Unmounted}
import japgolly.scalajs.react.extra.router.SetRouteVia.HistoryReplace
import japgolly.scalajs.react.extra.router.{RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._
import mahalla.components.{AboutUs, CreatePerson, Index, News, PersonInfo, Services}
import mahalla.layout.{Footer, Header}
import mahalla.notification.Notification
import org.scalajs.dom.document

object TopLevelComponent extends AjaxImplicits {

  case class GlobalState(
    userInfo: Option[UserInfo] = None,
    setUser: Option[UserInfo] => Callback = _ => Callback.empty
  ) {
    def isAuthed: Boolean = document.cookie.contains("tsec-auth-cookie")
  }

  object GlobalState {
    val ctx: Context[GlobalState] = React.createContext("GlobalState", GlobalState())
  }

  sealed trait QueryPage {
    val queryParams: Map[String, String]

    def toQueryParam: String = queryParams
      .map { case (k, v) =>
        s"$k=$v"
      }
      .reduceOption(_ + "&" + _)
      .fold("")("?" + _)
  }

  sealed abstract class AppPage(val name: String)

  object AppPage {
    case object NewsPage extends AppPage("News")

    case object ServicesPage extends AppPage("Services")

    case object IndexPage  extends AppPage("Home")

    case object AboutUsPage extends AppPage("AboutUs")

    case object AddPersonPage extends AppPage("AddPerson")

    case object PersonInfoPage extends AppPage("PersonInfo")
  }

  val baseUrl: BaseUrl = BaseUrl.fromWindowOrigin_/

  val routerConfig: RouterConfig[AppPage] =
    RouterConfigDsl[AppPage].buildConfig { dsl =>
      import dsl._
      val rules =
        List(
          trimSlashes,
          staticRoute(root, IndexPage) ~> renderR(ctl => Index.component(Index.Props(ctl))),
          staticRoute("services", ServicesPage) ~> renderR(ctl => Services.component(Services.Props(ctl))),
          staticRoute("aboutUs", AboutUsPage) ~> renderR(ctl => AboutUs.component(AboutUs.Props(ctl))),
          staticRoute("news", NewsPage) ~> renderR(ctl => News.component(News.Props(ctl))),
          staticRoute("addPerson", AddPersonPage) ~> renderR(ctl => CreatePerson.component(CreatePerson.Props(ctl))),
          staticRoute("personInfo", PersonInfoPage) ~> renderR(ctl => PersonInfo.component(PersonInfo.Props(ctl)))
        )
      rules
        .reduce(_ | _)
        .notFound(redirectToPage(IndexPage)(HistoryReplace))
        .renderWith((ctl, r) =>
          <.div(
            Notification.render(),
            Header.component(Header.Props(ctl, r.page)),
            r.render(),
            Footer()
          )
        )
    }

  val router: RouterF[CallbackTo, AppPage] =
    Router(BaseUrl.fromWindowOrigin_/, routerConfig)

  class Backend($: BackendScope[_, GlobalState]) {

    def render(implicit state: GlobalState): VdomNode =
      GlobalState.ctx.provide(state.copy(setUser = user => $.modState(_.copy(userInfo = user)))) {
        router()
      }
  }

  private val Component: Component[Unit, GlobalState, Backend, CtorType.Nullary] =
    ScalaComponent
      .builder[Unit]
      .initialState(GlobalState())
      .renderBackend[Backend]
      .build

  def apply(): Unmounted[Unit, GlobalState, Backend] = Component()
}

