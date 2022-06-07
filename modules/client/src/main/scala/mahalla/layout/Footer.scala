package mahalla.layout

import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js

object Footer {

  def apply(): VdomElement =
    <.footer(
      ^.className := "footer",
      ^.style := js.Dictionary("background-image" -> "url('assets/img/map.png')"),
      <.div(
        ^.className := "footer-top",
        <.div(
          ^.className := "container",
          <.div(
            ^.className := "row",
            <.div(
              ^.className := "col-lg-4 col-md-4 col-12",
              <.div(
                ^.className := "single-widget footer-about widget",
                <.div(
                  ^.className := "logo",
                  <.div(
                    ^.className := "img-logo",
                    <.a(
                      ^.className := "logo",
                      ^.href := "index.html",
                      <.h1(
                        ^.style := js.Dictionary("color" -> "#ffff"),
                        "Mahalla")
                    )
                  )
                ),
                <.div(
                  ^.className := "footer-widget-about-description",
                  <.p("Mahalla Fuqarolar yig'ini faoliyati haqida ma'lumot beruvchi veb sayt")
                ),
                <.div(
                  ^.className := "social",
                  <.ul(
                    ^.className := "social-icons",
                    <.li(
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.i(
                          ^.className := "fa fa-facebook")
                      )
                    ),
                    <.li(
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.i(
                          ^.className := "fa fa-instagram")
                      )
                    ),
                    <.li(
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.i(
                          ^.className := "fa fa-telegram")
                      )
                    )
                  )
                ),
                <.div(
                  ^.className := "button",
                  <.a(
                    ^.href := "#",
                    ^.className := "bizwheel-btn",
                    "About Us")
                )
              )
            ),
            <.div(
              ^.className := "col-lg-4 col-md-4 col-12",
              <.div(
                ^.className := "single-widget",
                <.h3(
                  ^.className := "widget-title",
                  "Manzil"),
                <.div(
                  ^.style := js.Dictionary("width" -> "100%"),
                  <.iframe(
                    ^.src := "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2411.385043248726!2d60.58901027832622!3d41.53063273013664!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x41dfc8595b178eef%3A0x8af1e9e5a7ec16b4!2sGHJR%2B6HM%2C%20Urganch%2C%20O%60zbekiston!5e1!3m2!1suz!2s!4v1652093854766!5m2!1suz!2s",
                    ^.width := "100%",
                    ^.height := "300",
                    ^.style := js.Dictionary("border" -> "0"),
                    ^.allowFullScreen := true,
                    VdomAttr("loading") := "lazy",
                    VdomAttr("referrerpolicy") := "no-referrer-when-downgrade")
                )
              )
            ),
            <.div(
              ^.className := "col-lg-3 col-md-4 col-12",
              <.div(
                ^.className := "single-widget widget",
                <.h3(
                  ^.className := "widget-title",
                  "Bog'lanish"),
                <.ul(
                  ^.className := "address-widget-list",
                  <.li(
                    ^.className := "footer-mobile-number",
                    <.i(
                      ^.className := "fa fa-phone"),
                    "+(998) 1234567"
                  ),
                  <.li(
                    ^.className := "footer-mobile-number",
                    <.i(
                      ^.className := "fa fa-envelope"),
                    "info@mahallam.uz"
                  ),
                  <.li(
                    ^.className := "footer-mobile-number",
                    <.i(
                      ^.className := "fa fa-map-marker"),
                    "Adolat mahallasi"
                  )
                )
              )
            )
          ),
          <.div(
            ^.className := "row",
            <.div(
              ^.className := "col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12",
              <.div(
                ^.className := "footer-newsletter",
                <.form(
                  ^.action := "#",
                  ^.method := "post",
                  ^.className := "newsletter-area",
                  <.input(
                    ^.`type` := "email",
                    ^.placeholder := "Your email address"),
                  <.button(
                    ^.`type` := "submit",
                    "Sign Up")
                )
              )
            )
          )
        )
      ),
      <.div(
        ^.className := "copyright",
        <.div(
          ^.className := "container",
          <.div(
            ^.className := "row",
            <.div(
              ^.className := "col-12",
              <.div(
                ^.className := "copyright-content",
                <.p("© Copyright",
                  <.a(
                    ^.href := "#",
                    "Mahalla"),
                  ". Design & Development By",
                  <.a(
                    ^.target := "_blank",
                    ^.href := "#",
                    "Matkarimov")
                )
              )
            )
          )
        )
      )
    )

}

