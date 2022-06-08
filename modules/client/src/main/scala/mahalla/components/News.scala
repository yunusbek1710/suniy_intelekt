package mahalla.components

import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage

import scala.scalajs.js

object News extends AjaxImplicits {

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
                        <.a(^.href := "news.html","Yangiliklar")
                      )
                    )
                  ),
                  <.div(^.className := "bread-title",
                    <.h2("Yangiliklar & E'lonlar")
                  )
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
                    <.img(^.src := "/assets/img/news-1.jpg", ^.alt := "news"),
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
                    <.img(^.src := "/assets/img/raisvakolat.jpg", ^.alt := "rais-vakolatlari"),
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
            ),
            <.div(^.className := "row",
              <.div(^.className := "col-12",
                <.div(^.className := "pagination-plugin",
                  <.ul(^.className := "pagination-list",
                    <.li(^.className := "prev",
                      <.a(^.href := "#","Prev")
                    ),
                    <.li(^.className := "page-numbers current",
                      <.a(^.href := "#","1")
                    ),
                    <.li(^.className := "page-numbers",
                      <.a(^.href := "#","2")
                    ),
                    <.li(^.className := "page-numbers",
                      <.a(^.href := "#","3")
                    ),
                    <.li(^.className := "next",
                      <.a(^.href := "#","Next")
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

