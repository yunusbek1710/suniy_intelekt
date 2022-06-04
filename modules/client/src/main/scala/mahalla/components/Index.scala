package mahalla.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage

import scala.scalajs.js

object Index extends AjaxImplicits {

  case class State(
                    busyLoader: Boolean = false
                  )

  class Backend($: Hooks.UseState[State]) {

    def account: VdomArray =
      VdomArray(
        <.section(
          ^.cls := "hero-slider style1")(
          <.div(
            ^.className := "home-slider")(
            <.div(
              ^.className := "single-slider",
              ^.style := js.Dictionary("background-image" -> "url('img/banner-1.jpg')"),
              <.div(
                ^.className := "container",
                <.div(
                  ^.className := "row",
                  <.div(
                    ^.className := "col-lg-7 col-md-8 col-12",
                    <.div(
                      ^.className := "welcome-text",
                      <.div(
                        ^.className := "hero-text",
                        <.h4("18.04.2022"),
                        <.br,
                        <.h4("Mahallada yangi Guzar qurib bitkazildi va foydalanishga topshirildi")
                      )
                    )
                  )
                )
              )
            ),
            <.div(
              ^.className := "single-slider",
              ^.style := js.Dictionary("background-image" -> "url('img/banner-2.jpg')"),
              <.div(
                ^.className := "container",
                <.div(
                  ^.className := "row",
                  <.div(
                    ^.className := "col-lg-7 col-md-8 col-12",
                    <.div(
                      ^.className := "welcome-text",
                      <.div(
                        ^.className := "hero-text",
                        <.h4("05.05.2022"),
                        <.br,
                        <.h4("Yangi bolalar maydonchasi foydalanishga topshirildi"),
                        <.div(
                          ^.className := "p-text",
                          <.p(
                            ^.className := "bg-light",
                            """Nunc tincidunt venenatis elit. Etiam venenatis quam vel maximus bibendum
                                            Pellentesque elementum dapibus diam tristique""")
                        )
                      )
                    )
                  )
                )
              )
            ),
            <.div(
              ^.className := "single-slider",
              ^.style := js.Dictionary("background-image" -> "url('img/banner-3.jpg')"),
              <.div(
                ^.className := "container",
                <.div(
                  ^.className := "row",
                  <.div(
                    ^.className := "col-lg-7 col-md-8 col-12",
                    <.div(
                      ^.className := "welcome-text",
                      <.div(
                        ^.className := "hero-text",
                        <.h4("10.04.2022"),
                        <.h4("Hashar 2022, Aprel"),
                        <.div(
                          ^.className := "p-text",
                          <.p(
                            ^.className := "bg-light",
                            "Mahallada hashar o'tkazildi va ko'chalar obod qilindi.")
                        ),
                        <.div(
                          ^.className := "button",
                          <.a(
                            ^.href := "#",
                            ^.className := "bizwheel-btn theme-2 effect",
                            "Batafsil")
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        ),
        <.section(
          ^.className := "latest-blog section-bg section-space",
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12",
                <.div(
                  ^.className := "section-title default text-center",
                  <.div(
                    ^.className := "section-top",
                    <.h1(
                      <.span("So'nggi"),
                      <.b("Yangiliklar")
                    )
                  )
                )
              )
            ),
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "blog-latest blog-latest-slider",
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-news",
                      <.div(
                        ^.className := "news-head overlay",
                        <.span(
                          ^.className := "news-img",
                          ^.style := js.Dictionary("background-image" -> "//via.placeholder.com/700x530')")),
                        <.a(
                          ^.href := "#",
                          ^.className := "bizwheel-btn theme-2",
                          "Read more")
                      ),
                      <.div(
                        ^.className := "news-body",
                        <.div(
                          ^.className := "news-content",
                          <.h3(
                            ^.className := "news-title",
                            <.a(
                              ^.href := "blog-single.html",
                              """We Provide you Best &
                                            Creative Consulting Service""")
                          ),
                          <.div(
                            ^.className := "news-text",
                            <.p(
                              """Sed tempus pulvinar augue ut euismod. Donec a nisi
                                            volutpat, dignissim mauris eget. Quisque vitae nunc sit amet eros
                                            pellentesque tempus at sit amet sem. Maecenas feugiat mauris""")
                          ),
                          <.ul(
                            ^.className := "news-meta",
                            <.li(
                              ^.className := "date",
                              <.i(
                                ^.className := "fa fa-calendar"),
                              "April 2020"
                            )
                          )
                        )
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-news",
                      <.div(
                        ^.className := "news-head overlay",
                        <.span(
                          ^.className := "news-img",
                          ^.style := js.Dictionary("background-image" -> "//via.placeholder.com/700x530')")),
                        <.a(
                          ^.href := "#",
                          ^.className := "bizwheel-btn theme-2",
                          "Read more")
                      ),
                      <.div(
                        ^.className := "news-body",
                        <.div(
                          ^.className := "news-content",
                          <.h3(
                            ^.className := "news-title",
                            <.a(
                              ^.href := "blog-single.html",
                              """We Provide you Best &
                                            Creative Consulting Service""")
                          ),
                          <.div(
                            ^.className := "news-text",
                            <.p(
                              """Sed tempus pulvinar augue ut euismod. Donec a nisi
                                            volutpat, dignissim mauris eget. Quisque vitae nunc sit amet eros
                                            pellentesque tempus at sit amet sem. Maecenas feugiat mauris""")
                          ),
                          <.ul(
                            ^.className := "news-meta",
                            <.li(
                              ^.className := "date",
                              <.i(
                                ^.className := "fa fa-calendar"),
                              "April 2020"
                            )
                          )
                        )
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-news",
                      <.div(
                        ^.className := "news-head overlay",
                        <.span(
                          ^.className := "news-img",
                          ^.style := js.Dictionary("background-image" -> "//via.placeholder.com/700x530')")),
                        <.a(
                          ^.href := "#",
                          ^.className := "bizwheel-btn theme-2",
                          "Read more")
                      ),
                      <.div(
                        ^.className := "news-body",
                        <.div(
                          ^.className := "news-content",
                          <.h3(
                            ^.className := "news-title",
                            <.a(
                              ^.href := "blog-single.html",
                              """We Provide you Best &
                                            Creative Consulting Service""")
                          ),
                          <.div(
                            ^.className := "news-text",
                            <.p(
                              """Sed tempus pulvinar augue ut euismod. Donec a nisi
                                            volutpat, dignissim mauris eget. Quisque vitae nunc sit amet eros
                                            pellentesque tempus at sit amet sem. Maecenas feugiat mauris""")
                          ),
                          <.ul(
                            ^.className := "news-meta",
                            <.li(
                              ^.className := "date",
                              <.i(
                                ^.className := "fa fa-calendar"),
                              "April 2020"
                            )
                          )
                        )
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-news",
                      <.div(
                        ^.className := "news-head overlay",
                        <.span(
                          ^.className := "news-img",
                          ^.style := js.Dictionary("background-image" -> "//via.placeholder.com/700x530')")),
                        <.a(
                          ^.href := "#",
                          ^.className := "bizwheel-btn theme-2",
                          "Read more")
                      ),
                      <.div(
                        ^.className := "news-body",
                        <.div(
                          ^.className := "news-content",
                          <.h3(
                            ^.className := "news-title",
                            <.a(
                              ^.href := "blog-single.html",
                              """We Provide you Best &
                                            Creative Consulting Service""")
                          ),
                          <.div(
                            ^.className := "news-text",
                            <.p(
                              """Sed tempus pulvinar augue ut euismod. Donec a nisi
                                            volutpat, dignissim mauris eget. Quisque vitae nunc sit amet eros
                                            pellentesque tempus at sit amet sem. Maecenas feugiat mauris""")
                          ),
                          <.ul(
                            ^.className := "news-meta",
                            <.li(
                              ^.className := "date",
                              <.i(
                                ^.className := "fa fa-calendar"),
                              "April 2020"
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
        <.section(
          ^.className := "features-area section-bg",
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-lg-3 col-md-6 col-12",
                <.div(
                  ^.className := "single-feature",
                  <.div(
                    ^.className := "icon-head",
                    <.i(
                      ^.className := "fa fa-podcast")
                  ),
                  <.h4(
                    <.a(
                      ^.href := "service-single.html",
                      "Creative Design")
                  ),
                  <.p(
                    """Aenean aliquet rutrum enimn scelerisque. Nam dictumanpo, antequis laoreet ullamcorper,
                            velitsd odio scelerisque tod"""),
                  <.div(
                    ^.className := "button",
                    <.a(
                      ^.href := "service-single.html",
                      ^.className := "bizwheel-btn",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      "Learn More"
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-3 col-md-6 col-12",
                <.div(
                  ^.className := "single-feature",
                  <.div(
                    ^.className := "icon-head",
                    <.i(
                      ^.className := "fa fa-podcast")
                  ),
                  <.h4(
                    <.a(
                      ^.href := "service-single.html",
                      "Quality Service")
                  ),
                  <.p(
                    """Aenean aliquet rutrum enimn scelerisque. Nam dictumanpo, antequis laoreet ullamcorper,
                            velitsd odio scelerisque tod"""),
                  <.div(
                    ^.className := "button",
                    <.a(
                      ^.href := "service-single.html",
                      ^.className := "bizwheel-btn",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      "Learn More"
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-3 col-md-6 col-12",
                <.div(
                  ^.className := "single-feature active",
                  <.div(
                    ^.className := "icon-head",
                    <.i(
                      ^.className := "fa fa-podcast")
                  ),
                  <.h4(
                    <.a(
                      ^.href := "service-single.html",
                      "On-time Delivery")
                  ),
                  <.p(
                    """Aenean aliquet rutrum enimn scelerisque. Nam dictumanpo, antequis laoreet ullamcorper,
                            velitsd odio scelerisque tod"""),
                  <.div(
                    ^.className := "button",
                    <.a(
                      ^.href := "service-single.html",
                      ^.className := "bizwheel-btn",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      "Learn More"
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-3 col-md-6 col-12",
                <.div(
                  ^.className := "single-feature",
                  <.div(
                    ^.className := "icon-head",
                    <.i(
                      ^.className := "fa fa-podcast")
                  ),
                  <.h4(
                    <.a(
                      ^.href := "service-single.html",
                      "24/7 Live support")
                  ),
                  <.p(
                    """Aenean aliquet rutrum enimn scelerisque. Nam dictumanpo, antequis laoreet ullamcorper,
                            velitsd odio scelerisque tod"""),
                  <.div(
                    ^.className := "button",
                    <.a(
                      ^.href := "service-single.html",
                      ^.className := "bizwheel-btn",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      "Learn More"
                    )
                  )
                )
              )
            )
          )
        ),
        <.section(
          ^.className := "call-action overlay",
          ^.style := js.Dictionary("background-image" -> "//via.placeholder.com/1500x300')"),
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-lg-9 col-12",
                <.div(
                  ^.className := "call-inner",
                  <.h2("Brand Products & Creativity is our Fashion"),
                  <.p("ehicula maximus velit. Morbi non tincidunt purus, a hendrerit nisi. Vivamus elementum")
                )
              ),
              <.div(
                ^.className := "col-lg-3 col-12",
                <.div(
                  ^.className := "button",
                  <.a(
                    ^.href := "structure.html",
                    ^.className := "bizwheel-btn",
                    "Our Portfolio")
                )
              )
            )
          )
        ),
        <.section(
          ^.className := "services section-bg section-space",
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "section-title style2 text-center",
                  <.div(
                    ^.className := "section-top",
                    <.h1(
                      <.span("Creative"),
                      <.b("Service We Provide")
                    ),
                    <.h4(
                      """We provide quality service &
                            support..""")
                  ),
                  <.div(
                    ^.className := "section-bottom",
                    <.div(
                      ^.className := "text-style-two",
                      <.p(
                        """Aliquam Sodales Justo Sit Amet Urna Auctor Scelerisquinterdum Leo Anet Tempus Enim
                                    Esent Egetis Hendrerit Vel Nibh Vitae Ornar Sem Velit Aliquam""")
                    )
                  )
                )
              )
            ),
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-lg-4 col-md-4 col-12",
                <.div(
                  ^.className := "single-service",
                  <.div(
                    ^.className := "service-head",
                    <.img(
                      ^.src := "https://via.placeholder.com/555x410",
                      ^.alt := "#"),
                    <.div(
                      ^.className := "icon-bg",
                      <.i(
                        ^.className := "fa fa-handshake-o")
                    )
                  ),
                  <.div(
                    ^.className := "service-content",
                    <.h4(
                      <.a(
                        ^.href := "service-business.html",
                        "Business Strategy")
                    ),
                    <.p(
                      """Cras venenatis, purus sit amet tempus mattis, justo nisi facilisis metus, in tempus ipsum
                                ipsum eu ipsum. Class aptent taciti"""),
                    <.a(
                      ^.className := "btn",
                      ^.href := "service-business.html",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      """View
                                Service"""
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-4 col-md-4 col-12",
                <.div(
                  ^.className := "single-service",
                  <.div(
                    ^.className := "service-head",
                    <.img(
                      ^.src := "https://via.placeholder.com/555x410",
                      ^.alt := "#"),
                    <.div(
                      ^.className := "icon-bg",
                      <.i(
                        ^.className := "fa fa-html5")
                    )
                  ),
                  <.div(
                    ^.className := "service-content",
                    <.h4(
                      <.a(
                        ^.href := "service-develop.html",
                        "Web Development")
                    ),
                    <.p(
                      """Cras venenatis, purus sit amet tempus mattis, justo nisi facilisis metus, in tempus ipsum
                                ipsum eu ipsum. Class aptent taciti"""),
                    <.a(
                      ^.className := "btn",
                      ^.href := "service-develop.html",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      """View
                                Service"""
                    )
                  )
                )
              ),
              <.div(
                ^.className := "col-lg-4 col-md-4 col-12",
                <.div(
                  ^.className := "single-service",
                  <.div(
                    ^.className := "service-head",
                    <.img(
                      ^.src := "https://via.placeholder.com/555x410",
                      ^.alt := "#"),
                    <.div(
                      ^.className := "icon-bg",
                      <.i(
                        ^.className := "fa fa-cube")
                    )
                  ),
                  <.div(
                    ^.className := "service-content",
                    <.h4(
                      <.a(
                        ^.href := "service-market.html",
                        "Market Research")
                    ),
                    <.p(
                      """Cras venenatis, purus sit amet tempus mattis, justo nisi facilisis metus, in tempus ipsum
                                ipsum eu ipsum. Class aptent taciti"""),
                    <.a(
                      ^.className := "btn",
                      ^.href := "service-market.html",
                      <.i(
                        ^.className := "fa fa-arrow-circle-o-right"),
                      """View
                                Service"""
                    )
                  )
                )
              )
            )
          )
        ),
        <.section(
          ^.className := "portfolio section-space",
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12",
                <.div(
                  ^.className := "section-title default text-center",
                  <.div(
                    ^.className := "section-top",
                    <.h1(
                      <.span("Project"),
                      <.b("Our Works")
                    )
                  ),
                  <.div(
                    ^.className := "section-bottom",
                    <.div(
                      ^.className := "text",
                      <.p(
                        """Lorem Ipsum Dolor Sit Amet, Conse Ctetur Adipiscing Elit, Sed Do Eiusmod Tempor Ares
                                    Incididunt Utlabore. Dolore Magna Ones Baliqua""")
                    )
                  )
                )
              )
            ),
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "portfolio-menu",
                  <.ul(
                    ^.id := "portfolio-nav",
                    ^.className := "portfolio-nav tr-list list-inline cbp-l-filters-work",
                    <.li(
                      VdomAttr("data-filter") := "*",
                      ^.className := "cbp-filter-item active",
                      "All"),
                    <.li(
                      VdomAttr("data-filter") := ".animation",
                      ^.className := "cbp-filter-item",
                      "Animation"),
                    <.li(
                      VdomAttr("data-filter") := ".branding",
                      ^.className := "cbp-filter-item",
                      "Branding"),
                    <.li(
                      VdomAttr("data-filter") := ".business",
                      ^.className := "cbp-filter-item",
                      "Business"),
                    <.li(
                      VdomAttr("data-filter") := ".consulting",
                      ^.className := "cbp-filter-item",
                      "Consulting"),
                    <.li(
                      VdomAttr("data-filter") := ".marketing",
                      ^.className := "cbp-filter-item",
                      "Marketing"),
                    <.li(
                      VdomAttr("data-filter") := ".seo",
                      ^.className := "cbp-filter-item",
                      "SEO")
                  )
                )
              )
            ),
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "portfolio-main",
                  <.div(
                    ^.id := "portfolio-item",
                    ^.className := "portfolio-item-active",
                    <.div(
                      ^.className := "cbp-item business animation",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Business, Aniamtion")
                        )
                      )
                    ),
                    <.div(
                      ^.className := "cbp-item seo consulting",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Seo, Consulting")
                        )
                      )
                    ),
                    <.div(
                      ^.className := "cbp-item marketing seo",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Marketing, SEO")
                        )
                      )
                    ),
                    <.div(
                      ^.className := "cbp-item animation branding",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Animation, Branding")
                        )
                      )
                    ),
                    <.div(
                      ^.className := "cbp-item branding consulting",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Branding, Consulting")
                        )
                      )
                    ),
                    <.div(
                      ^.className := "cbp-item business marketing",
                      <.div(
                        ^.className := "single-portfolio",
                        <.div(
                          ^.className := "portfolio-head overlay",
                          <.img(
                            ^.src := "https://via.placeholder.com/600x415",
                            ^.alt := "#"),
                          <.a(
                            ^.className := "more",
                            ^.href := "portfolio-single.html",
                            <.i(
                              ^.className := "fa fa-long-arrow-right")
                          )
                        ),
                        <.div(
                          ^.className := "portfolio-content",
                          <.h4(
                            <.a(
                              ^.href := "portfolio-single.html",
                              "Creative Marketing")
                          ),
                          <.p("Business")
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
          ^.className := "clients section-bg",
          <.div(
            ^.className := "container",
            <.div(
              ^.className := "row",
              <.div(
                ^.className := "col-12",
                <.div(
                  ^.className := "partner-slider",
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-1.png",
                          ^.alt := "#")
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-2.png",
                          ^.alt := "#")
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-3.png",
                          ^.alt := "#")
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-4.png",
                          ^.alt := "#")
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-5.png",
                          ^.alt := "#")
                      )
                    )
                  ),
                  <.div(
                    ^.className := "single-slider",
                    <.div(
                      ^.className := "single-client",
                      <.a(
                        ^.href := "#",
                        ^.target := "_blank",
                        <.img(
                          ^.src := "img/client/client-6.png",
                          ^.alt := "#")
                      )
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

