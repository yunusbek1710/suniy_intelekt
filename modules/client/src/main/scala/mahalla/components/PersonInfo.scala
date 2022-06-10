package mahalla.components

import district.api.Urls.AdminData
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage
import org.scalajs.dom.HTMLInputElement
import district.domain.Person
import japgolly.scalajs.react.callback.Callback

import scala.scalajs.js

object PersonInfo extends AjaxImplicits {

  case class State(
    busyLoader: Boolean = false,
    person: Option[Person] = None,
    selected: String = "",
    personalInfos: List[Person] = Nil
  )

  class Backend($ : Hooks.UseState[State]) {

    def onChangeDocumentNumber(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(selected = e.target.value))

    def find: Callback =
      $.modState(_.copy(person = $.value.personalInfos.find(_.documentNumber.value == $.value.selected)))

    def account: TagMod =
      <.div(
        <.div(^.className := "container mt-5 mb-5")(
          <.nav(^.className := "navbar bg-light justify-content-center mb-5")(
            <.div(^.className := "form-inline")(
              <.p(^.className := "mr-3", ^.style := js.Dictionary("color" -> "#17206a"))("Hujjat seriyasi va raqami:"),
              <.input(
                ^.className := "form-control-lg mr-sm-2",
                ^.value     := $.value.selected,
                ^.onChange ==> onChangeDocumentNumber,
                ^.`type`               := "search",
                ^.placeholder          := "AA1234567",
                VdomAttr("aria-label") := "Search"
              ),
              <.button(^.className := "btn btn-outline-primary my-2 my-sm-0", ^.onClick --> find)("Qidirish")
            )
          ),
          <.div(^.className := "row")(
            <.div(^.className := "col")(
              $.value.person.fold(TagMod.empty) { p =>
                <.div(^.style := js.Dictionary("background" -> "#17206a"), ^.className := "p-4")(
                  <.div(^.className := "row")(
                    <.div(^.className  := "col table-bordered p-3")(
                      <.p(^.className  := "text-white", "Hujjat turi:"),
                      <.h5(^.className := "text-white", p.documentType.value),
                      <.p(^.className  := "text-white", "Hujjat raqami:"),
                      <.h5(^.className := "text-white", p.documentNumber.value),
                      <.p(^.className  := "text-white", "Telefon raqami:"),
                      <.h5(^.className := "text-white", p.phoneNumber)
                    ),
                    <.div(^.className  := "col table-bordered p-3")(
                      <.p(^.className  := "text-white", "Ism:"),
                      <.h5(^.className := "text-white", p.firstName.value),
                      <.p(^.className  := "text-white", "Familiya:"),
                      <.h5(^.className := "text-white", p.lastName.value),
                      <.p(^.className  := "text-white", "Otasining ismi:"),
                      <.h5(^.className := "text-white", p.fathersName.value)
                    )
                  ),
                  <.div(^.className := "row")(
                    <.div(^.className  := "col table-bordered p-3")(
                      <.p(^.className  := "mt-2 text-white", "Tug'ilgan sanasi:"),
                      <.h5(^.className := "text-white", p.birthday.value),
                      <.p(^.className  := "mt-3 text-white", "Manzil:"),
                      <.h5(^.className := "text-white", p.livingPlace.value),
                      <.p(^.className  := "mt-3 text-white", "Ko'cha:"),
                      <.h5(^.className := "text-white", p.street.value),
                      <.p(^.className  := "mt-3 text-white", "Uy raqami:"),
                      <.h5(^.className := "text-white", p.houseNumber),
                      <.p(^.className  := "mt-3 text-white", "Jinsi:"),
                      <.h5(^.className := "text-white", p.gender.value),
                      <.p(^.className  := "mt-3 text-white", "Bandlik holati:"),
                      <.h5(^.className := "text-white", p.employmentStatus.value),
                      <.p(^.className  := "mt-3 text-white", "Oilaviy ahvoli:"),
                      <.h5(^.className := "text-white", p.familyStatus.value),
                      <.p(^.className  := "mt-3 text-white", "Salomatlik holati:"),
                      <.h5(^.className := "text-white", p.healthStatus.value),
                      <.p(^.className  := "mt-3 text-white", "Ta'lim holati:"),
                      <.h5(^.className := "text-white")(p.educationalInfo.value),
                      <.p(^.className  := "mt-3 text-white", "Yoshlar daftaridagi holati:"),
                      <.h5(^.className := "text-white", p.youthNote.value),
                      <.p(^.className  := "mt-3 text-white", "Temir daftaridagi holati:"),
                      <.h5(^.className := "text-white", p.ironNote.value),
                      <.p(^.className  := "mt-3 text-white", "Ayollar daftaridagi holati:"),
                      <.h5(^.className := "text-white", p.womenNote.value)
                    )
                  )
                )
              }
            )
          ).when($.value.person.nonEmpty)
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
      .useEffectOnMountBy { (_: Props, $ : Hooks.UseState[State]) =>
        get(AdminData.infos)
          .fail(onError)
          .done[List[Person]] { result =>
            $.modState(_.copy(personalInfos = result))
          }
          .asCallback
      }
      .render { (_: Props, $ : Hooks.UseState[State]) =>
        new Backend($).render
      }
}
