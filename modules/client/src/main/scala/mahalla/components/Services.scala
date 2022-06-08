package mahalla.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage

import scala.scalajs.js

object Services extends AjaxImplicits {

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
                        <.a(^.href := "services.html","Xizmatlar")
                      )
                    )
                  ),
                  <.div(^.className := "bread-title",
                    <.h2("Bizning xizmatlar")
                  )
                )
              )
            )
          )
        ),
        <.section(^.className := "services section-bg section-space",
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-12",
                <.div(^.className := "section-title  style2 text-center",
                  <.div(^.className := "section-top",
                    <.h1(
                      <.span("Bizning"),
                      <.b("Xizmatlar")
                    )
                  )
                )
              )
            ),
            <.div(^.className := "row",
              <.div(^.className := "col-lg-4 col-md-6 col-12",
                <.div(^.className := "single-service",
                  <.div(^.className := "service-content",
                    <.h4(
                      <.a(^.href := "https://my.gov.uz/oz/pension","Pensiya (nafaqa) haqida ma’lumot")
                    ),
                    <.a(^.className := "btn", ^.href := "https://my.gov.uz/oz/pension",
                      <.i(^.className := "fa fa-arrow-circle-o-right"),
                      "View Service"
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-4 col-md-6 col-12",
                <.div(^.className := "single-service",
                  <.div(^.className := "service-content",
                    <.h4(
                      <.a(^.href := "https://my.gov.uz/oz/pinfl-info","Doimiy (vaqtinchalik) yashash joyi bo'yicha ma'lumot")
                    ),
                    <.a(^.className := "btn", ^.href := "https://my.gov.uz/oz/pinfl-info",
                      <.i(^.className := "fa fa-arrow-circle-o-right"),
                      "View Service"
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-4 col-md-6 col-12",
                <.div(^.className := "single-service",
                  <.div(^.className := "service-content",
                    <.h4(
                      <.a(^.href := "https://my.gov.uz/oz/kindergarten/default/create","Bolani bog'chaga joylashtirish uchun ariza berish")
                    ),
                    <.a(^.className := "btn", ^.href := "https://my.gov.uz/oz/kindergarten/default/create",
                      <.i(^.className := "fa fa-arrow-circle-o-right"),
                      "View Service"
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-4 col-md-6 col-12",
                <.div(^.className := "single-service",
                  <.div(^.className := "service-content",
                    <.h4(
                      <.a(^.href := "https://yoshlardaftari.uz/#/citizens/check-in-notebook",""""Yoshlar daftari" dagi holatni tekshirish""")
                    ),
                    <.a(^.className := "btn", ^.href := "https://yoshlardaftari.uz/#/citizens/check-in-notebook",
                      <.i(^.className := "fa fa-arrow-circle-o-right"),
                      "View Service"
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-4 col-md-6 col-12",
                <.div(^.className := "single-service",
                  <.div(^.className := "service-content",
                    <.h4(
                      <.a(^.href := "https://my.gov.uz/oz/university-reference","O'qish joyidan ma'lumotnoma olish (oliy ta'lim muassasasidan)")
                    ),
                    <.a(^.className := "btn", ^.href := "https://my.gov.uz/oz/university-reference",
                      <.i(^.className := "fa fa-arrow-circle-o-right"),
                      "View Service"
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

