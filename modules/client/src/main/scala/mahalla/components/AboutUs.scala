package mahalla.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage

import scala.scalajs.js

object AboutUs extends AjaxImplicits {

  case class State(
                    busyLoader: Boolean = false
                  )

  class Backend($: Hooks.UseState[State]) {

    def account: VdomArray =
      VdomArray(
        <.div(^.className := "breadcrumbs overlay", ^.style := js.Dictionary("background-image" -> "url('/assets/img/bannerbreadcrumb.jpg')"),
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-12",
                <.div(^.className := "bread-inner",
                  <.div(^.className := "bread-menu",
                    <.ul(
                      <.li(
                        <.a(^.href := "index.html","Bosh sahifa")
                      ),
                      <.li(
                        <.a(^.href := "about.html","Tizim haqida")
                      )
                    )
                  ),
                  <.div(^.className := "bread-title",
                    <.h2("Tizim haqida")
                  )
                )
              )
            )
          )
        ),
        <.section(^.className := "about-us section-space",
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-lg-5 offset-lg-1 col-md-6 col-12",
                <.div(^.className := "modern-img-feature",
                  <.img(^.src := "/assets/img/banner-1.jpg", ^.alt := "#")
                )
              ),
              <.div(^.className := "col-lg-5 col-md-6 col-12",
                <.div(^.className := "about-content section-title default text-left",
                  <.div(^.className := "section-top",
                    <.h1(
                      <.span("Tizim"),
                      <.b("Tizim haqida ma'lumot")
                    )
                  ),
                  <.div(^.className := "section-bottom",
                    <.div(^.className := "text",
                      <.p("Ushbu veb-sayt mahalla va mahalla yashovchi aholi haqidagi qo'lyozma ma'lumotlarni raqamlashtirish uchun xizmat qiladi."),
                      <.p("Ushbu ma'lumotlarni raqamli ko'rinishga keltirish mahalla fuqarolar yig'ini hodimlariga aholiga xizmat ko'rsatishda va MFYda axborot texnologiyalarini qo'llashda yordam beradi.")
                    )
                  )
                )
              )
            )
          )
        )
      )

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

