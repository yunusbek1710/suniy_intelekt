package mahalla.components

import district.api.Urls.Common
import domain.ContactUsParams
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.hooks.Hooks
import mahalla.mask.InputMask
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage
import mahalla.notification.Notification
import org.scalajs.dom.HTMLInputElement

import scala.scalajs.js

object ContactUs extends AjaxImplicits {
  case class State(
    busyLoader: Boolean = false,
    contactParams: ContactUsParams = ContactUsParams()
  )

  class Backend($ : Hooks.UseState[State]) {


    def onChangeFirstName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(contactParams = $.value.contactParams.copy(firstName = e.target.value)))

    def onChangeLastName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(contactParams = $.value.contactParams.copy(lastName = e.target.value)))

    def onChangePhone(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(contactParams = $.value.contactParams.copy(phone = e.target.value)))

    def onChangeDescription(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(contactParams = $.value.contactParams.copy(description = e.target.value)))

    def send: Callback =
      if ($.value.contactParams.firstName.isEmpty)
        Notification.error("Please select your first name!")
      else if ($.value.contactParams.lastName.isEmpty)
        Notification.error("Please enter your last name!")
      else if ($.value.contactParams.phone.isEmpty)
        Notification.error("Please enter your phone!")
      else if ($.value.contactParams.description.isEmpty)
        Notification.error("Please enter your description!")
      else
        $.modState(_.copy(busyLoader = true)) >> {
          post[ContactUsParams](url = Common.contact, $.value.contactParams)
            .fail(onError)
            .doneWithoutContent {
              Notification.success("Survey sent!") >>
                $.modState(_.copy(contactParams = ContactUsParams(), busyLoader = false))
            }
            .asCallback
        }

    def account: VdomArray =
      VdomArray(
        <.div(
          ^.className := "breadcrumbs overlay",
          ^.style     := js.Dictionary("background-image" -> "url('/assets/img/bannerbreadcrumb.jpg')"),
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "bread-inner",
                  <.div(
                    ^.className := "bread-menu",
                    <.ul(
                      <.li(
                        <.a(^.href := "", "Bosh sahida")
                      ),
                      <.li(
                        <.a(^.href := "", "Bog'lanish")
                      )
                    )
                  ),
                  <.div(^.className := "bread-title", <.h2("Biz bilan bog'lanish"))
                )
              )
            )
          )
        ),
        <.section(^.className := "contact-us section-space")(
          <.div(^.className := "container")(
            <.div(^.className := "row")(
              <.div(^.className := "col-lg-7 col-md-7 col-12")(
                <.div(^.className := "contact-form-area m-top-30")(
                  <.h4("Savol va murojaat yo'llash"),
                  <.div(^.className := "form")(
                    <.div(^.className := "row")(
                      <.div(^.className := "col-lg-6 col-md-6 col-12")(
                        <.div(^.className := "form-group")(
                          <.div(^.className := "icon")(
                            <.i(^.className := "fa fa-user")
                          ),
                          <.input(
                            ^.`type` := "text",
                            ^.name := "first_name",
                            ^.placeholder := "First Name",
                            ^.value := $.value.contactParams.firstName,
                            ^.onChange ==> onChangeFirstName
                          )
                        )
                      ),
                      <.div(^.className := "col-lg-6 col-md-6 col-12")(
                        <.div(^.className := "form-group")(
                          <.div(^.className := "icon")(
                            <.i(^.className := "fa fa-user")
                          ),
                          <.input(
                            ^.`type` := "text",
                            ^.name := "last_name",
                            ^.placeholder := "Last Name",
                            ^.value := $.value.contactParams.lastName,
                            ^.onChange ==> onChangeLastName
                          )
                        )
                      ),
                      <.div(^.className := "col-lg-12 col-md-12 col-12")(
                        <.div(^.className   := "form-group")(
                          <.div(^.className := "icon", <.i(^.className := "fa fa-phone")),
                          InputMask(
                            `type` = "text",
                            name = "phone",
                            value     = $.value.contactParams.phone,
                            onChange = onChangePhone,
                            mask = "99 999-99-99",
                            placeholder = "+998"
                          )
                        )
                      ),
                      <.div(^.className := "col-12")(
                        <.div(^.className     := "form-group textarea")(
                          <.div(^.className   := "icon", <.i(^.className := "fa fa-pencil")),
                          <.textarea(
                            ^.`type` := "textarea",
                            ^.name := "message",
                            ^.rows := 5,
                            ^.value := $.value.contactParams.description,
                            ^.onChange ==> onChangeDescription
                          )
                        )
                      ),
                      <.div(^.className := "col-12")(
                        <.div(^.className := "form-group button")(
                          <.button(^.className := "bizwheel-btn theme-2", ^.onClick --> send)("Yuborish")
                        )
                      )
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-5 col-md-5 col-12",
                <.div(
                  ^.className := "contact-box-main m-top-30",
                  <.div(
                    ^.className := "contact-title",
                    <.h2("Biz bilan bog'lanish"),
                    <.p("Tizim bo'yicha kamchilik yoki savollaringiz bo'lsa biz bilan bog'laning")
                  ),
                  <.div(
                    ^.className := "single-contact-box",
                    <.div(^.className := "c-icon", <.i(^.className := "fa fa-clock-o")),
                    <.div(^.className := "c-text", <.h4("Ish vaqti"), <.p("Dushanba - Shanba", <.br, "09:00 - 18:00"))
                  ),
                  <.div(
                    ^.className := "single-contact-box",
                    <.div(^.className := "c-icon", <.i(^.className := "fa fa-phone")),
                    <.div(
                      ^.className := "c-text",
                      <.h4("Qo'ng'iroq qiling"),
                      <.p("Tel.: +998 12 345 6789", <.br, "Mob.: +998 12 345 6789")
                    )
                  ),
                  <.div(
                    ^.className := "single-contact-box",
                    <.div(^.className := "c-icon", <.i(^.className := "fa fa-envelope-o")),
                    <.div(^.className := "c-text", <.h4("E-mail pochta"), <.p("info@mahallam.uz"))
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
      .render { (_: Props, $ : Hooks.UseState[State]) =>
        new Backend($).render
      }

}
