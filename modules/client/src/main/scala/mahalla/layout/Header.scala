package mahalla.layout

import district.api.Urls.UserData
import district.domain.{UserForm, UserInfo}
import district.refinements.{EmailAddress, Password}
import domain.{SignInParams, SignUpParams}
import eu.timepit.refined.types.all.NonEmptyString
import io.udash.wrappers.jquery.jQ
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtlF
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{CallbackTo, CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage._
import mahalla.TopLevelComponent.{AppPage, GlobalState}
import mahalla.components.Index.loadJs
import mahalla.notification.Notification
import org.scalajs.dom.{HTMLElement, HTMLInputElement}

import scala.scalajs.js

object Header extends AjaxImplicits {

  case class State(
    signUpParams: SignUpParams = SignUpParams(),
    signInParams: SignInParams = SignInParams(),
    busyLoader: Boolean = false,
    showModal: Boolean = true
  )

  def getUser($ : Hooks.UseState[State], ctx: GlobalState): Callback =
    get(UserData.user)
      .fail(onError)
      .done[Option[UserInfo]] { result =>
        ctx.setUser(result)
      }
      .asCallback
      .when_(ctx.isAuthed)

  class Backend(props: Props, $ : Hooks.UseState[State], ctx: GlobalState) {

    def isValidPassword(password: String): Boolean = {
      val regex = "^(?=.*\\d)(?=.*[/\\[\\]{}()*+?.,_^$|#!@%&\\s])(?=.*[A-Z])[a-zA-Z\\d-/\\]\\[{}()*+?.,_^$|#!@%&\\s]{6,32}$".r
      regex.pattern.matcher(password).find
    }

    def onChangeName(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(name = e.target.value)))

    def onChangeEmail(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(email = e.target.value)))

    def onChangePassword(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(password = e.target.value)))

    def onChangeConfirmPassword(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signUpParams = $.value.signUpParams.copy(confirmPassword = e.target.value)))

    def haveDigit(password: String): Boolean = {
      val digit = "\\d".r
      digit.pattern.matcher(password).find
    }

    def haveLowerCaseLetter(password: String): Boolean = {
      val lower = "[a-z]".r
      lower.pattern.matcher(password).find
    }

    def haveUpperCaseLetter(password: String): Boolean = {
      val upper = "[A-Z]".r
      upper.pattern.matcher(password).find
    }

    def haveSpecialCharacter(password: String): Boolean = {
      val upper = "[ `!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~]".r
      upper.pattern.matcher(password).find
    }

    def registerModal: VdomArray =
      VdomArray(
        <.div(
          ^.cls                       := "modal fade",
          (^.cls                      := "modal fade").when($.value.showModal),
          ^.id                        := "registerModal",
          ^.tabIndex                  := -1,
          VdomAttr("aria-labelledby") := "registerModalLabel",
          VdomAttr("aria-hidden")     := "true"
        )(
          <.div(^.cls := "modal-dialog")(
            <.div(^.cls := "modal-content")(
              <.div(^.cls := "modal-header")(
                <.h5(^.cls := "modal-title", ^.id := "registerModalLabel")("Register Form")
              ),
              <.div(^.cls := "modal-body")(
                <.div(^.cls := "registration__hr")(
                  <.hr,
                  <.span(^.cls := "registration__hr-label")("Or Sign Up Another Email")
                ),
                <.div(^.cls := "row")(
                  <.div(^.id := "content", ^.cls := "col-12")(
                    <.form(
                      ^.action  := "",
                      ^.method  := "post",
                      ^.encType := "multipart/form-data",
                      ^.cls     := "form-horizontal account-register clearfix"
                    )(
                      <.fieldset(^.id := "account", <.legend(^.cls := "fs-5")("Your Personal Details"))(
                        <.div(^.cls := "form-group required", ^.style := js.Dictionary("display" -> "none"))(
                          <.label(^.cls := "col-12 control-label")("Customer Group"),
                          <.div(^.cls := "col-12")(
                            <.div(^.cls := "radio")(
                              <.label(
                                <.input(
                                  ^.`type`  := "radio",
                                  ^.name    := "customer_group_id",
                                  ^.value   := "1",
                                  ^.checked := true
                                ),
                                "Default"
                              )
                            )
                          )
                        ),
                        <.div(^.cls := "form-group required")(
                          <.label(^.cls := "col-12 control-label", ^.`for` := "input-fullname")("Full name"),
                          <.div(^.cls := "col-12")(
                            <.input(
                              ^.`type` := "text",
                              ^.name   := "name",
                              ^.onChange ==> onChangeName,
                              ^.value       := $.value.signUpParams.name,
                              ^.placeholder := "Full name",
                              ^.id          := "input-fullname",
                              ^.cls         := "form-control"
                            )
                          )
                        ),
                        <.div(^.cls := "form-group required")(
                          <.label(^.cls := "col-12 control-label", ^.`for` := "input-email")("E-Mail"),
                          <.div(^.cls := "col-12")(
                            <.input(
                              ^.`type` := "email",
                              ^.name   := "email",
                              ^.onChange ==> onChangeEmail,
                              ^.value       := $.value.signUpParams.email,
                              ^.placeholder := "E-Mail",
                              ^.id          := "input-email",
                              ^.cls         := "form-control"
                            )
                          )
                        )
                      ),
                      <.fieldset(^.cls := "mt-2")(
                        <.legend(^.cls := "fs-5")("Your Password"),
                        <.div(^.cls := "form-group required")(
                          <.label(^.cls := "col-12 control-label", ^.`for` := "input-password")("Password"),
                          <.div(^.cls := "col-12")(
                            <.div(^.cls := "input-group position-relative")(
                              <.input(
                                ^.`type`      := "password",
                                ^.cls         := "form-control",
                                ^.name        := "password",
                                ^.id          := "input-password",
                                ^.placeholder := "Password",
                                ^.onChange ==> onChangePassword,
                                ^.value := $.value.signUpParams.password
                              ),
                              <.i(
                                ^.cls := "fa-solid fa-eye-slash pointer eye",
                                ^.onClick ==> hideShowPassword
                              )
                            ),
                            <.ul(^.cls := "validation-notify")(
                              if (!haveLowerCaseLetter($.value.signUpParams.password)) {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("A lowercase letter")
                                )
                              } else if (!haveUpperCaseLetter($.value.signUpParams.password)) {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("A capital (uppercase) letter")
                                )
                              } else if (!haveDigit($.value.signUpParams.password)) {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("A number")
                                )
                              } else if (!haveSpecialCharacter($.value.signUpParams.password)) {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("A special character")
                                )
                              } else if (!($.value.signUpParams.password.length >= 6)) {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("Minimal length 6 characters")
                                )
                              } else {
                                <.li(^.cls  := "text-success")(
                                  <.i(^.cls := "fa fa-check me-2"),
                                  <.span("Your password contains all required characters")
                                )
                              }
                            )
                          )
                        ),
                        <.div(^.cls := "form-group required")(
                          <.label(^.cls := "col-12 control-label", ^.`for` := "input-confirm")("Password Confirm"),
                          <.div(^.cls := "col-12")(
                            <.div(^.cls := "input-group position-relative")(
                              <.input(
                                ^.`type`      := "password",
                                ^.cls         := "form-control",
                                ^.name        := "confirm",
                                ^.id          := "input-confirm",
                                ^.placeholder := "Confirm password",
                                ^.onChange ==> onChangeConfirmPassword,
                                ^.value := $.value.signUpParams.confirmPassword
                              ),
                              <.i(
                                ^.cls := "fa-solid fa-eye-slash pointer eye",
                                ^.onClick ==> hideShowPassword
                              )
                            ),
                            <.ul(^.cls := "validation-notify")(
                              if (
                                $.value.signUpParams.password.nonEmpty && $.value.signUpParams.confirmPassword.nonEmpty
                                  && ($.value.signUpParams.password == $.value.signUpParams.confirmPassword)
                              ) {
                                <.li(^.cls  := "text-success")(
                                  <.i(^.cls := "fa fa-check me-2"),
                                  <.span("All right!")
                                )
                              } else {
                                <.li(^.cls  := "text-danger")(
                                  <.i(^.cls := "fa fa-circle me-2"),
                                  <.span("Your password doesn't match")
                                )
                              }
                            )
                          )
                        )
                      )
                    )
                  )
                )
              ),
              <.div(^.cls := "modal-footer")(
                <.button(^.cls := "btn btn-secondary", VdomAttr("data-dismiss") := "modal")("Close"),
                <.a(^.href := "#", ^.cls := "btn btn-primary text-white", ^.onClick --> register)("Register")
              )
            )
          )
        )
      )

    def register: Callback =
      if ($.value.signUpParams.name.isEmpty)
        Notification.error("Please enter your name!")
      else if ($.value.signUpParams.email.isEmpty)
        Notification.error("Please enter your email!")
      else if ($.value.signUpParams.email.nonEmpty && !isValidEmail($.value.signUpParams.email))
        Notification.error("Please enter correct email address! For example email@email.email")
      else if ($.value.signUpParams.password.isEmpty)
        Notification.error("Please enter your password!")
      else if ($.value.signUpParams.password.nonEmpty && !isValidPassword($.value.signUpParams.password))
        Notification.error("Enter the password that matches the requested request!")
      else if ($.value.signUpParams.confirmPassword.isEmpty)
        Notification.error("Please enter confirm password!")
      else if ($.value.signUpParams.password != $.value.signUpParams.confirmPassword)
        Notification.error("Please enter correct password and confirm password!")
      else {
        $.modState(_.copy(busyLoader = true)) >> {
          val userForm: UserForm = UserForm(
            fullName = NonEmptyString.unsafeFrom($.value.signUpParams.name),
            email = EmailAddress.unsafeFrom($.value.signUpParams.email),
            password = Password.unsafeFrom($.value.signUpParams.password)
          )
          post[UserForm](url = UserData.register, userForm)
            .fail(onError)
            .doneWithoutContent {
              Notification.success("You successfully registered!") >>
                $.modState(_.copy(signUpParams = SignUpParams(), showModal = false, busyLoader = false)) >>
                Callback(jQ(".modal-backdrop").css("opacity", 0).css("display", "none")) >>
                Callback(jQ("#registerModal").css("display", "none")) >>
                Callback(jQ("body").css("overflow-y", "scroll"))
            }
            .asCallback
        }
      }

    def onChangeEmailForLogin(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signInParams = $.value.signInParams.copy(email = e.target.value)))

    def onChangePasswordForLogin(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(signInParams = $.value.signInParams.copy(password = e.target.value)))

    def isValidEmail(email: String): Boolean = {
      val regex = "^[a-zA-Z0-9.-_]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$".r
      regex.pattern.matcher(email).find
    }

    def hideShowPassword: SyntheticEvent[HTMLElement] => Callback = e => {
      Callback(
        if (jQ(e.currentTarget).hasClass("fa-eye-slash")) {
          jQ(e.currentTarget).removeClass("fa-eye-slash").addClass("fa-eye")
          jQ(e.currentTarget).prev("input").attr("type", "text")
        } else {
          jQ(e.currentTarget).removeClass("fa-eye").addClass("fa-eye-slash")
          jQ(e.currentTarget).prev("input").attr("type", "password")
        }
      )
    }

    def login: Callback =
      if ($.value.signInParams.email.isEmpty)
        Notification.error("Please enter your email!")
      else if ($.value.signInParams.email.nonEmpty && !isValidEmail($.value.signInParams.email))
        Notification.error("Please enter correct email address! For example email@email.email")
      else if ($.value.signInParams.password.isEmpty) Notification.error("Please enter your password!")
      else
        post[SignInParams](url = UserData.login, $.value.signInParams)
          .fail(onError)
          .doneWithoutContent {
            $.modState(_.copy(signInParams = SignInParams(), showModal = false)) >>
              getUser($, ctx) >>
              Callback(jQ(".modal-backdrop").css("opacity", 0).css("display", "none")) >>
              Callback(jQ("#loginModal").css("display", "none")) >>
              Callback(jQ("body").css("overflow-y", "scroll"))
          }
          .asCallback

    def loginModal: VdomArray =
      VdomArray(
        <.div(
          ^.cls                       := "modal fade",
          (^.cls                      := "modal fade").when($.value.showModal),
          ^.id                        := "loginModal",
          ^.tabIndex                  := -1,
          VdomAttr("aria-labelledby") := "loginModalLabel",
          VdomAttr("aria-hidden")     := "true"
        )(
          <.div(^.cls := "modal-dialog")(
            <.div(^.cls := "modal-content")(
              <.div(^.cls := "modal-header")(
                <.h5(^.cls := "modal-title", ^.id := "loginModalLabel")("Login Form")
              ),
              <.div(^.cls := "modal-body")(
                <.button(^.cls := "btn btn-white form-control border my-1")(
                  <.img(
                    ^.src    := "../assets/icons/google.svg",
                    ^.cls    := "me-2",
                    ^.width  := "20px",
                    ^.height := "20px",
                    ^.alt    := "#"
                  ),
                  <.span("Continue with Google")
                ),
                <.button(^.cls := "btn btn-white form-control border my-1")(
                  <.img(
                    ^.src    := "../assets/icons/apple.svg",
                    ^.cls    := "me-2",
                    ^.width  := "20px",
                    ^.height := "20px",
                    ^.alt    := "#"
                  ),
                  <.span("Continue with Apple")
                ),
                <.button(^.cls := "btn btn-white form-control border my-1")(
                  <.img(
                    ^.src    := "../assets/icons/facebook-f.svg",
                    ^.cls    := "me-2",
                    ^.width  := "20px",
                    ^.height := "20px",
                    ^.alt    := "#"
                  ),
                  <.span("Continue with Facebook")
                ),
                <.div(^.cls := "registration__hr")(
                  <.hr,
                  <.span(^.cls := "registration__hr-label")("Have a password? Continue with your email address")
                ),
                <.form(^.method := "post")(
                  <.div(^.cls := "customer-login")(
                    <.div(^.cls := "form-group")(
                      <.label(^.cls := "control-label", ^.`for` := "input-email-for-login")("Email"),
                      <.input(
                        ^.`type` := "text",
                        ^.name   := "email",
                        ^.onChange ==> onChangeEmailForLogin,
                        ^.value := $.value.signInParams.email,
                        ^.id    := "input-email-for-login",
                        ^.cls   := "form-control"
                      )
                    ),
                    <.div(^.cls := "form-group")(
                      <.label(^.cls := "control-label", ^.`for` := "input-password-for-login")("Password"),
                      <.div(^.cls := "input-group position-relative")(
                        <.input(
                          ^.`type` := "password",
                          ^.cls    := "form-control",
                          ^.name   := "password",
                          ^.id     := "input-password-for-login",
                          ^.onChange ==> onChangePasswordForLogin,
                          ^.value := $.value.signInParams.password
                        ),
                        <.i(
                          ^.cls := "fa-solid fa-eye-slash pointer eye",
                          ^.onClick ==> hideShowPassword
                        )
                      )
                    )
                  )
                )
              ),
              <.div(^.cls := "modal-footer")(
                <.button(
                  ^.cls := "btn btn-secondary",
                  ^.onClick --> Callback(jQ("body").css("overflow-y", "scroll")),
                  VdomAttr("data-dismiss") := "modal"
                )("Close"),
                <.a(^.href := "#", ^.cls := "btn btn-primary text-white", ^.onClick --> login)("Login")
              )
            )
          )
        )
      )

    def header: VdomArray =
      VdomArray(
        <.header(^.className := "header")(
          <.div(^.className := "topbar")(
            <.div(^.className := "container")(
              <.div(^.className := "row")(
                <.div(^.className := "col-lg-8 col-12")(
                  <.div(^.className := "top-contact")(
                    <.div(
                      ^.className := "single-contact",
                      <.i(^.className := "fa fa-phone"),
                      "Phone: +(998) 1234567"
                    ),
                    <.div(
                      ^.className := "single-contact",
                      <.i(^.className := "fa fa-envelope-open"),
                      "Email: info@mahallam.uz"
                    ),
                    <.div(
                      ^.className := "single-contact",
                      <.i(^.className := "fa fa-clock-o"),
                      "Ish vaqti: 08:30 - 19:00"
                    )
                  )
                ),
                <.div(^.className := "col-lg-4 col-12")(
                  <.div(
                    ^.className := "topbar-right",
                    <.ul(
                      ^.className := "social-icons",
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(^.className := "fa fa-facebook")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(^.className := "fa fa-instagram")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "#",
                          <.i(^.className := "fa fa-telegram")
                        )
                      ),
                      <.li(
                        <.a(
                          VdomAttr("data-toggle") := "modal",
                          VdomAttr("data-target") := "#loginModal",
                          <.i(^.className := "fa fa-user")(" LogIn")
                        )
                      ),
                      <.li(
                        <.a(
                          VdomAttr("data-toggle") := "modal",
                          VdomAttr("data-target") := "#registerModal",
                          <.i(^.className := "fa fa-user")(" Register")
                        )
                      ),
                      <.li(
                        <.a(
                          ^.href := "/user/logout",
                          <.i(^.className := "fa fa-sign-out")("LogOut")
                        )
                      ).when(ctx.isAuthed)
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
                              ^.href := "/",
                              <.h2("Mahalla")
                            )
                          )
                        ),
                        <.div(^.className := "mobile-nav")
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
                                    ^.id        := "nav",
                                    ^.className := "nav main-menu menu navbar-nav",
                                    <.li(
                                      <.a(props.ctl setOnClick IndexPage)("Bosh sahifa")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "" || ctx.userInfo.fold("")(_.role.value) == "user"),
                                    <.li(
                                      <.a(props.ctl setOnClick NewsPage)("Yangiliklar")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "" || ctx.userInfo.fold("")(_.role.value) == "user"),
                                    <.li(
                                      <.a(props.ctl setOnClick ServicesPage)("Xizmatlar")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "" || ctx.userInfo.fold("")(_.role.value) == "user"),
                                    <.li(
                                      <.a(props.ctl setOnClick AboutUsPage)("Tizim haqida")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "" || ctx.userInfo.fold("")(_.role.value) == "user"),
                                    <.li(
                                      <.a(^.href := "contact.html")("Bog'lanish")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "" || ctx.userInfo.fold("")(_.role.value) == "user"),
                                    <.li(
                                      <.a(props.ctl setOnClick AddPersonPage)("Add person")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "admin"),
                                    <.li(
                                      <.a(props.ctl setOnClick PersonInfoPage)("Person info")
                                    ).when(ctx.userInfo.fold("")(_.role.value) == "admin")
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
                                  <.i(^.className := "fa fa-search")
                                )
                              ),
                              <.li(
                                ^.className := "bar",
                                <.a(^.className := "fa fa-bars")
                              )
                            ),
                            <.div(
                              ^.className := "search-top",
                              <.form(
                                ^.action    := "#",
                                ^.className := "search-form",
                                ^.method    := "get",
                                <.input(
                                  ^.`type`      := "text",
                                  ^.name        := "s",
                                  ^.value       := "",
                                  ^.placeholder := "Search here"
                                ),
                                <.button(
                                  ^.`type` := "submit",
                                  ^.id     := "searchsubmit",
                                  <.i(^.className := "fa fa-search")
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
                <.i(^.className := "fa fa-close")
              )
            ),
            <.div(
              ^.className := "single-content",
              <.h4("Muhim havolalar"),
              <.ul(
                ^.className := "links",
                <.li(
                  <.a(^.href := "#", "Bosh sahifa")
                ),
                <.li(
                  <.a(^.href := "#", "Yangiliklar")
                ),
                <.li(
                  <.a(^.href := "#", "Xizmatlar")
                ),
                <.li(
                  <.a(^.href := "#", "Tizim haqida")
                ),
                <.li(
                  <.a(^.href := "#", "Bog'lanish")
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
                    <.i(^.className := "fa fa-facebook")
                  )
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    <.i(^.className := "fa fa-instagram")
                  )
                ),
                <.li(
                  <.a(
                    ^.href := "#",
                    <.i(^.className := "fa fa-telegram")
                  )
                )
              )
            )
          )
        )
      )

    def render: VdomArray =
      VdomArray(
        header,
        loginModal,
        registerModal
      )
  }

  case class Props(ctl: RouterCtlF[CallbackTo, AppPage], selectedPage: AppPage)

  val component: Component[Props, CtorType.Props] =
    ScalaFnComponent
      .withHooks[Props]
      .useState(State())
      .useContext(GlobalState.ctx)
      .useEffectOnMountBy { (props: Props, $ : Hooks.UseState[State], ctx: GlobalState) =>
        get(UserData.user)
          .fail(onError)
          .done[Option[UserInfo]] { result =>
            ctx.setUser(result)
          }
          .asCallback
          .when_(ctx.isAuthed)
      }
      .render { (props: Props, $ : Hooks.UseState[State], ctx: GlobalState) =>
        new Backend(props, $, ctx).render
      }
}
