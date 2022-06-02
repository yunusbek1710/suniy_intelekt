package suniyIntelekt.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.vdom.svg_<^
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import suniyIntelekt.AjaxImplicits
import suniyIntelekt.TopLevelComponent.AppPage

object Services extends AjaxImplicits {

  case class State(
                    busyLoader: Boolean = false
                  )

  class Backend($: Hooks.UseState[State]) {

    def account: VdomArray =
      VdomArray(
        <.div(^.cls := "module extra-layout1")(
          <.div(^.cls := "modcontent")(
            <.div(^.cls := "container my-3")(
              <.div(^.cls := "row")(
                <.div(^.id := "content", ^.cls := "col-sm-9")(
                  <.h2(^.cls := "title fs-4 mt-0")("My Account"),
                  <.p(^.cls := "lead", "Hello, ", <.strong("Username "))("- To update your account information."),
                  <.form(^.cls := "mb-2")(
                    <.div(^.cls := "row")(
                      <.div(^.cls := "col-sm-6")(
                        <.fieldset(
                          ^.id := "personal-details",
                          <.legend("Personal Details"),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "text",
                              ^.cls := "form-control fs-6",
                              ^.id := "input-firstname",
                              ^.placeholder := "First Name",
                              ^.value := "",
                              ^.name := "firstname"
                            )
                          ),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "text",
                              ^.cls := "form-control fs-6",
                              ^.id := "input-lastname",
                              ^.placeholder := "Last Name",
                              ^.value := "",
                              ^.name := "lastname"
                            )
                          ),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "email",
                              ^.cls := "form-control fs-6",
                              ^.id := "input-email",
                              ^.placeholder := "E-Mail",
                              ^.value := "",
                              ^.name := "email"
                            )
                          ),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "tel",
                              ^.cls := "form-control fs-6",
                              ^.id := "input-telephone",
                              ^.placeholder := "Telephone",
                              ^.value := "",
                              ^.name := "telephone"
                            )
                          ),
                          <.div(^.cls := "buttons clearfix mt-2")(
                            <.div(^.cls := "pull-right")(
                              <.input(^.`type` := "submit", ^.cls := "btn btn-md btn-danger", ^.value := "Save changes")
                            )
                          )
                        )
                      ),
                      <.div(^.cls := "col-sm-6")(
                        <.fieldset(
                          <.legend("Change Password"),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "password",
                              ^.cls := "form-control fs-6",
                              ^.placeholder := "Old Password",
                              ^.value := "",
                              ^.name := "old-password"
                            )
                          ),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "password",
                              ^.cls := "form-control fs-6",
                              ^.placeholder := "New Password",
                              ^.value := "",
                              ^.name := "new-password"
                            )
                          ),
                          <.div(^.cls := "form-group my-2")(
                            <.input(
                              ^.`type` := "password",
                              ^.cls := "form-control fs-6",
                              ^.id := "input-confirm",
                              ^.placeholder := "New Password Confirm",
                              ^.value := "",
                              ^.name := "new-confirm"
                            )
                          ),
                          <.div(^.cls := "buttons clearfix mt-2")(
                            <.div(^.cls := "pull-right")(
                              <.input(^.`type` := "submit", ^.cls := "btn btn-md btn-danger", ^.value := "Save")
                            )
                          )
                        )
                      )
                    )
                  )
                ),
                <.aside(^.cls := "col-sm-3 hidden-xs content-aside col-md-3", ^.id := "column-right")(
                  <.div(^.className := "box-account box-footer")(
                    <.h3(^.className := "fs-4")("Account"),
                    <.ul(^.className := "menu fs-5")(
                      <.li(
                        <.a(^.href := "/address", ^.className := "footer-link")("Address Books")
                      ),
                      <.li(
                        <.a(^.href := "/wishlist", ^.className := "footer-link")("Wishlist")
                      ),
                      <.li(
                        <.a(^.href := "/orders", ^.className := "footer-link")("Order History")
                      ),
                      <.li(
                        <.a(^.href := "/account", ^.className := "footer-link")("Returns")
                      ),
                      <.li(
                        <.a(^.href := "/account", ^.className := "footer-link")("Transactions")
                      )
                    )
                  )
                )
              )
            )
          )
        )
      )

    def busyLoader: TagMod =
      <.div(
        <.div(^.cls := "busy-overlay")(
          svg_<^.SvgTag("svg")(
            ^.cls := "spinner",
            VdomAttr("viewBox") := "0 0 50 50",
            svg_<^.SvgTag("circle")(
              ^.cls := "path",
              VdomAttr("cx") := "25",
              VdomAttr("cy") := "25",
              VdomAttr("r") := "20",
              VdomAttr("fill") := "none",
              VdomAttr("stroke-width") := "5"
            )
          )
        ).when($.value.busyLoader)
      )

    def render: VdomArray =
      VdomArray(
        <.div(
          <.div(^.cls := "main-container")(
            busyLoader,
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

