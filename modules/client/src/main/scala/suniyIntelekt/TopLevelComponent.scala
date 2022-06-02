package suniyIntelekt

import suniyIntelekt.TopLevelComponent.AppPage.{AboutUsPage, IndexPage, NewsPage, ServicesPage}
import district.domain.UserInfo
import japgolly.scalajs.react.React.Context
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.{BackendScope, Component, Unmounted}
import japgolly.scalajs.react.extra.router.SetRouteVia.HistoryReplace
import japgolly.scalajs.react.extra.router.{RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import suniyIntelekt.components.{AboutUs, News, Services}
import suniyIntelekt.notification.Notification

import java.util.UUID

object TopLevelComponent extends AjaxImplicits {

  case class GlobalState(
    userInfo: Option[UserInfo] = None,
    setUser: Option[UserInfo] => Callback = _ => Callback.empty
  ) {
    def isAuthed: Boolean = document.cookie.contains("ajs_anonymous_id")
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
  }

  val baseUrl: BaseUrl = BaseUrl.fromWindowOrigin_/

  val routerConfig: RouterConfig[AppPage] =
    RouterConfigDsl[AppPage].buildConfig { dsl =>
      import dsl._
      val rules =
        List(
          trimSlashes,
          staticRoute(root, NewsPage) ~> renderR(ctl => News.component(News.Props(ctl))),
          staticRoute("services", ServicesPage) ~> renderR(ctl => Services.component(Services.Props(ctl))),
          staticRoute("aboutUs", AboutUsPage) ~> renderR(ctl => AboutUs.component(AboutUs.Props(ctl)))
        )
      rules
        .reduce(_ | _)
        .notFound(redirectToPage(IndexPage)(HistoryReplace))
        .renderWith((ctl, r) =>
          <.div(
            Notification.render(),
//            Header.component(Header.Props(ctl, r.page)),
            r.render(),
//            Footer()
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

