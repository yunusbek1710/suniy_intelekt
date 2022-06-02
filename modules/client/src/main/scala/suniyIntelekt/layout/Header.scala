package suniyIntelekt.layout

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtlF
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.vdom.svg_<^
import japgolly.scalajs.react.{CallbackTo, CtorType, ScalaFnComponent}
import suniyIntelekt.AjaxImplicits
import suniyIntelekt.TopLevelComponent.{AppPage, GlobalState}

object Header extends AjaxImplicits {

  case class State(
                    cartCount: Long = 0L,
                    searchText: String = "",
                    busyLoader: Boolean = false,
                    showModal: Boolean = true
                  )

  class Backend(props: Props, $ : Hooks.UseState[State], ctx: GlobalState) {
//    def onChangeName(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(name = e.target.value)))
//
//    def onChangeEmail(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(email = e.target.value)))
//
//    def onChangePhone(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(phone = e.target.value)))
//
//    def onChangePassword(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(password = e.target.value)))
//
//    def onChangeConfirmPassword(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(confirmPassword = e.target.value)))

//    def register: Callback =
//      if ($.value.signUpParams.name.isEmpty)
//        Notification.error("Please enter your name!")
//      else if ($.value.signUpParams.email.isEmpty)
//        Notification.error("Please enter your email!")
//      else if ($.value.signUpParams.email.nonEmpty && !isValidEmail($.value.signUpParams.email))
//        Notification.error("Please enter correct email address! For example email@email.email")
//      else if ($.value.signUpParams.phone.isEmpty && !isValidPhoneNumber($.value.signUpParams.phone))
//        Notification.error("Please enter your phone number!")
//      else if ($.value.signUpParams.password.isEmpty)
//        Notification.error("Please enter your password!")
//      else if ($.value.signUpParams.password.nonEmpty && !isValidPassword($.value.signUpParams.password))
//        Notification.error("Enter the password that matches the requested request!")
//      else if ($.value.signUpParams.confirmPassword.isEmpty)
//        Notification.error("Please enter confirm password!")
//      else if ($.value.signUpParams.password != $.value.signUpParams.confirmPassword)
//        Notification.error("Please enter correct password and confirm password!")
//      else {
//        $.modState(_.copy(busyLoader = true)) >> {
//          val userForm: UserForm = UserForm(
//            name = NonEmptyString.unsafeFrom($.value.signUpParams.name),
//            phone = NonEmptyString.unsafeFrom($.value.signUpParams.phone),
//            email = EmailAddress.unsafeFrom($.value.signUpParams.email),
//            password = Password.unsafeFrom($.value.signUpParams.password)
//          )
//          post[UserForm](url = UserData.create, userForm)
//            .fail(onError)
//            .doneWithoutContent {
//              Notification.success("You successfully registered!") >>
//                $.modState(_.copy(signUpParams = SignUpParams(), showModal = false, busyLoader = false)) >>
//                Callback(jQ(".modal-backdrop").css("opacity", 0).css("display", "none")) >>
//                Callback(jQ("#registerModal").css("display", "none")) >>
//                Callback(jQ("body").css("overflow-y", "scroll"))
//            }
//            .asCallback
//        }
//      }
//
//    def onChangeEmailForLogin(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signInParams = $.value.signInParams.copy(email = e.target.value)))
//
//    def onChangeEmailForgot(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(forgotPassParams = $.value.forgotPassParams.copy(email = e.target.value)))
//
//    def onChangePasswordForLogin(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(signInParams = $.value.signInParams.copy(password = e.target.value)))
//
//    def onChangeSearchText(e: SyntheticEvent[HTMLInputElement]): Callback =
//      $.modState(_.copy(searchText = e.target.value))
//
//    def search(searchText: String): Callback =
//      if (searchText.isEmpty)
//        Notification.error("Enter name of product you are looking for!")
//      else
//        props.ctl.set(SearchPage(Map("name" -> searchText)))
//
//    def login: Callback =
//      if ($.value.signInParams.email.isEmpty)
//        Notification.error("Please enter your email!")
//      else if ($.value.signInParams.email.nonEmpty && !isValidEmail($.value.signInParams.email))
//        Notification.error("Please enter correct email address! For example email@email.email")
//      else if ($.value.signInParams.password.isEmpty)
//        Notification.error("Please enter your password!")
//      else {
//        val loginForm: EmailAndPassword = EmailAndPassword(
//          email = EmailAddress.unsafeFrom($.value.signInParams.email),
//          password = Password.unsafeFrom($.value.signInParams.password)
//        )
//        post[EmailAndPassword](url = UserData.login, loginForm)
//          .fail(onError)
//          .doneWithoutContent {
//            $.modState(_.copy(signInParams = SignInParams(), showModal = false)) >>
//              getUser($, ctx) >>
//              getCartCount($, ctx) >>
//              Callback(jQ(".modal-backdrop").css("opacity", 0).css("display", "none")) >>
//              Callback(jQ("#loginModal").css("display", "none")) >>
//              Callback(jQ("body").css("overflow-y", "scroll"))
//          }
//          .asCallback
//      }

//    def loginModal: VdomArray =
//      VdomArray(
//        <.div(
//          ^.cls                       := "modal fade",
//          (^.cls                      := "modal fade").when($.value.showModal),
//          ^.id                        := "loginModal",
//          ^.tabIndex                  := -1,
//          VdomAttr("aria-labelledby") := "loginModalLabel",
//          VdomAttr("aria-hidden")     := "true"
//        )(
//          <.div(^.cls := "modal-dialog")(
//            <.div(^.cls := "modal-content")(
//              <.div(^.cls := "modal-header")(
//                <.h5(^.cls := "modal-title", ^.id := "loginModalLabel")("Login Form"),
//                <.button(
//                  ^.cls := "btn-close",
//                  ^.onClick --> Callback(jQ("body").css("overflow-y", "scroll")),
//                  VdomAttr("data-bs-dismiss") := "modal",
//                  VdomAttr("aria-label")      := "Close"
//                )
//              ),
//              <.div(^.cls := "modal-body")(
//                <.button(^.cls := "btn btn-white form-control border my-1")(
//                  <.img(
//                    ^.src    := "../assets/icons/google.svg",
//                    ^.cls    := "me-2",
//                    ^.width  := "20px",
//                    ^.height := "20px",
//                    ^.alt    := "#"
//                  ),
//                  <.span("Continue with Google")
//                ),
//                <.button(^.cls := "btn btn-white form-control border my-1")(
//                  <.img(
//                    ^.src    := "../assets/icons/apple.svg",
//                    ^.cls    := "me-2",
//                    ^.width  := "20px",
//                    ^.height := "20px",
//                    ^.alt    := "#"
//                  ),
//                  <.span("Continue with Apple")
//                ),
//                <.button(^.cls := "btn btn-white form-control border my-1")(
//                  <.img(
//                    ^.src    := "../assets/icons/facebook-f.svg",
//                    ^.cls    := "me-2",
//                    ^.width  := "20px",
//                    ^.height := "20px",
//                    ^.alt    := "#"
//                  ),
//                  <.span("Continue with Facebook")
//                ),
//                <.div(^.cls := "registration__hr")(
//                  <.hr,
//                  <.span(^.cls := "registration__hr-label")("Have a password? Continue with your email address")
//                ),
//                <.form(^.method := "post")(
//                  <.div(^.cls := "customer-login")(
//                    <.div(^.cls := "form-group")(
//                      <.label(^.cls := "control-label", ^.`for` := "input-email-for-login")("Email"),
//                      <.input(
//                        ^.`type` := "text",
//                        ^.name   := "email",
//                        ^.onChange ==> onChangeEmailForLogin,
//                        ^.value := $.value.signInParams.email,
//                        ^.id    := "input-email-for-login",
//                        ^.cls   := "form-control"
//                      )
//                    ),
//                    <.div(^.cls := "form-group")(
//                      <.label(^.cls := "control-label", ^.`for` := "input-password-for-login")("Password"),
//                      <.div(^.cls := "input-group position-relative")(
//                        <.input(
//                          ^.`type` := "password",
//                          ^.cls    := "form-control",
//                          ^.name   := "password",
//                          ^.id     := "input-password-for-login",
//                          ^.onChange ==> onChangePasswordForLogin,
//                          ^.value := $.value.signInParams.password
//                        ),
//                        <.i(
//                          ^.cls := "fa-solid fa-eye-slash pointer eye",
//                          ^.onClick ==> hideShowPassword
//                        )
//                      )
//                    )
//                  )
//                )
//              ),
//              <.div(
//                ^.cls                      := "modal-footer",
//                VdomAttr("data-bs-toggle") := "modal",
//                VdomAttr("data-bs-target") := "#forgotModal"
//              )(
//                <.a(^.cls := "ms-2 me-auto")("Forgot password?"),
//                <.button(
//                  ^.cls := "btn btn-secondary",
//                  ^.onClick --> Callback(jQ("body").css("overflow-y", "scroll")),
//                  VdomAttr("data-bs-dismiss") := "modal"
//                )("Close"),
//                <.a(^.href := "#", ^.cls := "btn btn-primary text-white", ^.onClick --> login)("Login")
//              )
//            )
//          )
//        )
//      )

    def header: VdomArray =
      VdomArray(
        <.header(
          ^.className := "header",
          <.div(
            ^.className := "topbar",
            <.div(
              ^.className := "container",
              <.div(
                ^.className := "row",
                <.div(
                  ^.className := "col-lg-8 col-12",
                  <.div(
                    ^.className := "top-contact",
                    <.div(
                      ^.className := "single-contact",
                      <.i(
                        ^.className := "fa fa-phone"),
                      "Phone: +(998) 1234567"
                    ),
                    <.div(
                      ^.className := "single-contact",
                      <.i(
                        ^.className := "fa fa-envelope-open"),
                      "Email: info@mahallam.uz"
                    ),
                    <.div(
                      ^.className := "single-contact",
                      <.i(
                        ^.className := "fa fa-clock-o"),
                      "Ish vaqti: 08:30 - 19:00"
                    )
                  )
                ),
                <.div(
                  ^.className := "col-lg-4 col-12",
                  <.div(
                    ^.className := "topbar-right",
                    <.ul(
                      ^.className := "social-icons",
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(
                            ^.className := "fa fa-facebook")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(
                            ^.className := "fa fa-instagram")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(
                            ^.className := "fa fa-telegram")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(
                            ^.className := "fa fa-user",
                            "LogIn")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(
                            ^.className := "fa fa-sign-out",
                            "LogOut")
                        )
                      )
                    )
                  )
                )
              )
            )
          ),
          <.div(
            ^.className := "middle-header",
            <.div(
              ^.className := "container",
              <.div(
                ^.className := "row",
                <.div(
                  ^.className := "col-12",
                  <.div(
                    ^.className := "middle-inner",
                    <.div(
                      ^.className := "row",
                      <.div(
                        ^.className := "col-lg-2 col-md-3 col-12",
                        <.div(
                          ^.className := "logo",
                          <.div(
                            ^.className := "img-logo",
                            <.a(
                              ^.href := "index.html",
                              <.h2("Mahalla")
                            )
                          )
                        ),
                        <.div(
                          ^.className := "mobile-nav")
                      ),
                      <.div(
                        ^.className := "col-lg-10 col-md-9 col-12",
                        <.div(
                          ^.className := "menu-area",
                          <.nav(
                            ^.className := "navbar navbar-expand-lg",
                            <.div(
                              ^.className := "navbar-collapse",
                              <.div(
                                ^.className := "nav-inner",
                                <.div(
                                  ^.className := "menu-home-menu-container",
                                  <.ul(
                                    ^.id := "nav",
                                    ^.className := "nav main-menu menu navbar-nav",
                                    <.li(
                                      <.a(
                                        ^.href := "index.html",
                                        "Bosh sahifa")
                                    ),
                                    <.li(
                                      <.a(
                                        ^.href := "news.html",
                                        "Yangiliklar")
                                    ),
                                    <.li(
                                      <.a(
                                        ^.href := "structure.html",
                                        "Tuzilma")
                                    ),
                                    <.li(
                                      <.a(
                                        ^.href := "services.html",
                                        "Xizmatlar")
                                    ),
                                    <.li(
                                      <.a(
                                        ^.href := "about.html",
                                        "Tizim haqida")
                                    ),
                                    <.li(
                                      <.a(
                                        ^.href := "contact.html",
                                        "Bog'lanish")
                                    ),
                                    "Matkarim 911 Beline, [02/06/2022 15:36]"
                                  )
                                )
                              )
                            )
                          ),
                          <.div(
                            ^.className := "right-bar",
                            <.ul(
                              ^.className := "right-nav",
                              <.li(
                                ^.className := "top-search",
                                <.a(
                                  ^.href := "#0",
                                  <.i(
                                    ^.className := "fa fa-search")
                                )
                              ),
                              <.li(
                                ^.className := "bar",
                                <.a(
                                  ^.className := "fa fa-bars")
                              )
                            ),
                            <.div(
                              ^.className := "search-top",
                              <.form(
                                ^.action := "#",
                                ^.className := "search-form",
                                ^.method := "get",
                                <.input(
                                  ^.`type` := "text",
                                  ^.name := "s",
                                  ^.value := "",
                                  ^.placeholder := "Search here"),
                                <.button(
                                  ^.`type` := "submit",
                                  ^.id := "searchsubmit",
                                  <.i(
                                    ^.className := "fa fa-search")
                                )
                              )
                            )
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          ),
          <.div(
            ^.className := "sidebar-popup",
            <.div(
              ^.className := "cross",
              <.a(
                ^.className := "btn",
                <.i(
                  ^.className := "fa fa-close")
              )
            ),
            <.div(
              ^.className := "single-content",
              <.h4("Muhim havolalar"),
              <.ul(
                ^.className := "links",
                <.li(
                  <.a(
                    ^.href := "#",
                    "Bosh sahifa")
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    "Yangiliklar")
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    "Tuzilma")
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    "Xizmatlar")
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    "Tizim haqida")
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    "Bog'lanish")
                )
              )
            ),
            <.div(
              ^.className := "single-content",
              <.h4("Ijtimoiy tarmoqlar"),
              <.ul(
                ^.className := "social",
                <.li(
                  <.a(
                    ^.href := "#",
                    <.i(
                      ^.className := "fa fa-facebook")
                  )
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    <.i(
                      ^.className := "fa fa-instagram")
                  )
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    <.i(
                      ^.className := "fa fa-telegram")
                  )
                )
              )
            )
          )
        )
      )

    def render: VdomArray =
      VdomArray(
        header
      )
  }

  case class Props(ctl: RouterCtlF[CallbackTo, AppPage], selectedPage: AppPage)

  val component: Component[Props, CtorType.Props] =
    ScalaFnComponent
      .withHooks[Props]
      .useState(State())
      .useContext(GlobalState.ctx)
      .render { (props: Props, $ : Hooks.UseState[State], ctx: GlobalState) =>
        new Backend(props, $, ctx).render
      }
}