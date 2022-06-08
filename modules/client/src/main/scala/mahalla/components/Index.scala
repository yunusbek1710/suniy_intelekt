package mahalla.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage
import org.scalajs.dom.{HTMLScriptElement, Node, document}

import scala.scalajs.js

object Index extends AjaxImplicits {

  case class State(
    busyLoader: Boolean = false
  )

  def loadJs(path: String): Node = {
    val script = document.createElement("script").asInstanceOf[HTMLScriptElement]
    script.src = path
    document.body.appendChild(script)
  }

  class Backend($ : Hooks.UseState[State]) {

    def account: VdomArray =
      VdomArray(
        <.section(^.className := "hero-slider style1")(
          <.div(^.className := "home-slider")(
            <.div(^.className := "single-slider slider-image")(
              <.div(^.className := "container")(
                <.div(^.className := "row")(
                  <.div(^.className := "col-lg-7 col-md-8 col-12")(
                    <.div(^.className := "welcome-text")(
                      <.div(^.className := "hero-text")(
                        <.h4("18.04.2022"),
                        <.br,
                        <.h4("Mahallada yangi Guzar qurib bitkazildi va foydalanishga topshirildi")
                      )
                    )
                  )
                )
              )
            ),
          )
        ),
        <.section(^.className := "latest-blog section-bg section-space",
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-lg-6 offset-lg-3 col-md-8 offset-md-2 col-12",
                <.div(^.className := "section-title default text-center",
                  <.div(^.className := "section-top",
                    <.h1(
                      <.span("So'nggi"),
                      <.b("Yangiliklar")
                    )
                  )
                )
              )
            ),
            <.section(^.className := "blog-layout news-default section-space",
              <.div(^.className := "container",
                <.div(^.className := "row",
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/news-1.jpg", ^.alt := "#"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "May 29, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "300"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "#","Mahalla fuqarolar yig‘inlarining yangi binolari foydalanishga topshirildi")
                          ),
                          <.div(^.className := "news-text",
                            <.p("""Andijon, Oltinko‘l tumanida "Keksalar haftaligi" doirasida "Andijon" hamda “Ayshaxonim” mahalla fuqarolar yig‘ini yangi binolarining ochilish marosimi bo‘lib o‘tdi.""")
                          ),
                          <.a(^.href := "blog-single.html", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
                          )
                        )
                      )
                    )
                  ),
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/ko'chat.jpg", ^.alt := "ko'chat ekish"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "May 10, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "199"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "#","Parkent tumanida erta tongdan ko‘chat ekish tadbirlari")
                          ),
                          <.div(^.className := "news-text",
                            <.p("Daraxt ekib, bog‘ yaratish xalqimiz turmush tarziga xos bo‘lgan milliy qadriyatlarimizdan biri.")
                          ),
                          <.a(^.href := "#", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
                          )
                        )
                      )
                    )
                  ),
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/raisvakolat.jpg", ^.alt := "rais vakolatlari"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "Aprel 30, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "280"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "","Mahalla raislarining vakolatlari kengaytiriladi")
                          ),
                          <.div(^.className := "news-text",
                            <.p("Prezident mahalliy ijro organlari, vakillik idoralari hamda mahallaning institutsional asoslarini yanada takomillashtirish lozimligini ta’kidladi.")
                          ),
                          <.a(^.href := "", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
                          )
                        )
                      )
                    )
                  ),
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/saylov.jpg", ^.alt := "rais saylovi"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "Aprel 19, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "410"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "","O'zbekistonda mahalla raislari saylovi boshlandi")
                          ),
                          <.div(^.className := "news-text",
                            <.p("O‘zbekistonda fuqarolar yig‘inlari raislari (oqsoqollari) saylovi boshlandi. Mamlakatda 9349 ta fuqarolar yig‘inlari mavjud.")
                          ),
                          <.a(^.href := "", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
                          )
                        )
                      )
                    )
                  ),
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/majlis.jpg", ^.alt := "tanqid"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "Aprel 15, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "190"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "#","Mahalla tizimidagi kamchiliklar tanqid qilindi")
                          ),
                          <.div(^.className := "news-text",
                            <.p("Prezidentimiz mahalla tizimidan xalq ham, davlat ham to‘la rozi emasligini, bu sohada kamchilik va dolzarb masalalar ko‘pligini ta’kidladi.")
                          ),
                          <.a(^.href := "blog-single.html", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
                          )
                        )
                      )
                    )
                  ),
                  <.div(^.className := "col-lg-4 col-md-6 col-12",
                    <.div(^.className := "single-news",
                      <.div(^.className := "news-head overlay",
                        <.img(^.src := "/assets/img/obod.jpg", ^.alt := "#"),
                        <.ul(^.className := "news-meta",
                          <.li(^.className := "author",
                            <.a(^.href := "#",
                              <.i(^.className := "fa fa-user"),
                              "admin"
                            )
                          ),
                          <.li(^.className := "date",
                            <.i(^.className := "fa fa-calendar"),
                            "Aprel 1, 2022"
                          ),
                          <.li(^.className := "view",
                            <.i(^.className := "fa fa-comments"),
                            "290"
                          )
                        )
                      ),
                      <.div(^.className := "news-body",
                        <.div(^.className := "news-content",
                          <.h3(^.className := "news-title",
                            <.a(^.href := "#","“Obod va xavfsiz mahalla” tamoyili amalga oshiriladi")
                          ),
                          <.div(^.className := "news-text",
                            <.p("Prezident Shavkat Mirziyoyev Oliy Majlisga yaqinda yo‘llagan Murojaatnomasida mahalla tizimini o‘zgartirish, “Obod va xavfsiz mahalla” tamoyilini joriy etish zarurligi to‘g‘risida fikr bildirgan edi.")
                          ),
                          <.a(^.href := "blog-single.html", ^.className := "more","Batafsil o'qish",
                            <.i(^.className := "fa fa-angle-double-right", VdomAttr("aria-hidden") := "true")
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
        <.section(^.className := "features-area section-bg",
          <.div(^.className := "container",
            <.h3("Foydali havolalar"),
            <.div(^.className := "row",
              <.div(^.className := "col-lg-3 col-md-6 col-12",
                <.div(^.className := "single-feature",
                  <.div(
                    <.img(^.style := js.Dictionary("height" -> "160px"), ^.src := "/assets/img/oliymajlis.png", ^.alt := "oliy majlis")
                  ),
                  <.h4(
                    <.a(^.href := "https://parliament.gov.uz/uz/","O'zbekiston Respublikasi Oliy Majlis qonunchilik palatasi rasmiy veb-sayti")
                  ),
                  <.div(^.className := "button",
                    <.a(^.href := "https://parliament.gov.uz/uz/", ^.className := "bizwheel-btn",
                      <.i(^.className := "fa fa-arrow-circle-o-right")
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-3 col-md-6 col-12",
                <.div(^.className := "single-feature",
                  <.div(
                    <.img(^.src := "/assets/img/gerb.png", ^.alt := "prezident uz")
                  ),
                  <.h5(
                    <.a(^.href := "https://president.uz/uz"),
                    "O'zbekiston Respublikasi Prezidentining rasmiy veb-sayti"
                  ),
                  <.div(^.className := "button",
                    <.a(^.href := "https://president.uz/uz", ^.className := "bizwheel-btn",
                      <.i(^.className := "fa fa-arrow-circle-o-right")
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-3 col-md-6 col-12",
                <.div(^.className := "single-feature active",
                  <.div(
                    <.img(^.style := js.Dictionary("height" -> "182px"), ^.src := "/assets/img/lexuz.png", ^.alt := "lex uz")
                  ),
                  <.h4(
                    <.a(^.href := "https://www.lex.uz/","LEX.UZ O'zbekiston Respublikasi Qonun hujjatlari ma'lumotlari")
                  ),
                  <.div(^.className := "button",
                    <.a(^.href := "https://www.lex.uz/", ^.className := "bizwheel-btn",
                      <.i(^.className := "fa fa-arrow-circle-o-right")
                    )
                  )
                )
              ),
              <.div(^.className := "col-lg-3 col-md-6 col-12",
                <.div(^.className := "single-feature",
                  <.div(
                    <.img(^.style := js.Dictionary("height" -> "205px"), ^.src := "/assets/img/gerb.png", ^.alt := "gov uz Hukumat")
                  ),
                  <.h4(
                    <.a(^.href := "https://www.gov.uz/uz","O'zbekiston Respublikasi Hukumat portali")
                  ),
                  <.div(^.className := "button",
                    <.a(^.href := "https://www.gov.uz/uz", ^.className := "bizwheel-btn",
                      <.i(^.className := "fa fa-arrow-circle-o-right")
                    )
                  )
                )
              )
            )
          )
        ),
        <.section(^.className := "call-action overlay",
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-12",
                <.div(^.className := "call-inner",
                  <.h4(^.className := "text-white","Islohotlarimiz natijalari, boʼlayotgan oʼzgarishlar va aholining kayfiyati avvalo mahallada seziladi. Shu maʼnoda, “mahalla – yorugʼ yuzimiz va vijdonimiz koʼzgusi”, desak, toʼgʼri boʼladi."),
                  <.p(^.className := "text-right","Shavkat Mirziyoyev")
                )
              )
            )
          )
        ),
        <.section(^.className := "services section-bg section-space",
          <.div(^.className := "container",
            <.div(^.className := "row",
              <.div(^.className := "col-12",
                <.div(^.className := "section-title  style2",
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
      .render { (_: Props, $ : Hooks.UseState[State]) =>
        new Backend($).render
      }
}
