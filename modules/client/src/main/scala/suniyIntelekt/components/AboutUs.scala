package suniyIntelekt.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import suniyIntelekt.AjaxImplicits
import suniyIntelekt.TopLevelComponent.AppPage

object AboutUs extends AjaxImplicits {

  case class State(
                    busyLoader: Boolean = false
                  )

  class Backend($: Hooks.UseState[State]) {

    def account: VdomArray =
      VdomArray()

    def render: VdomArray =
      VdomArray(
        <.div(
          <.div(^.cls := "main-container")(
            <.div(^.id := "content")(
              account
            )
          )
        )
      )

  }

  case class Props(ctl: RouterCtl[AppPage])

  val component: Component[Props, CtorType.Props] =
    ScalaFnComponent
      .withHooks[Props]
      .useState(State())
      .render { (_: Props, $: Hooks.UseState[State]) =>
        new Backend($).render
      }
}

