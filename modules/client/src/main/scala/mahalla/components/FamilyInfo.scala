package mahalla.components

import district.api.Urls.AdminData
import district.domain.Person
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage
import org.scalajs.dom.HTMLInputElement

import scala.scalajs.js

object FamilyInfo extends AjaxImplicits {

  case class State(
    busyLoader: Boolean = false,
    families: List[Person] = Nil,
    street: String = "",
    houseNumber: Int = 0,
    personalInfos: List[Person] = Nil
  )

  class Backend($ : Hooks.UseState[State]) {

    def onChangeStreet(e: SyntheticEvent[HTMLInputElement]): Callback =
      $.modState(_.copy(street = e.target.value))

    def onChangeHouseNumber(e: SyntheticEvent[HTMLInputElement]): Callback = {
      val number = e.target.value.replaceAll("e", "").toIntOption.getOrElse(0)
      $.modState(_.copy(houseNumber = number))
    }

    def find: Callback =
      $.modState(
        _.copy(families =
          $.value.personalInfos.filter(p => p.street.value.trim.toLowerCase == $.value.street.trim.toLowerCase && p.houseNumber == $.value.houseNumber)
        )
      )

    def info: TagMod =
      <.div(^.className := "container mt-5 mb-5")(
        <.nav(^.className := "navbar bg-light justify-content-center mb-5")(
          <.div(^.className := "form-inline")(
            <.p(^.className := "mr-3", ^.style := js.Dictionary("color" -> "#17206a"))("Ko'cha nomi:"),
            <.input(
              ^.className := "form-control-lg mr-sm-2",
              ^.value     := $.value.street,
              ^.onChange ==> onChangeStreet,
              ^.`type`               := "text",
              VdomAttr("aria-label") := "Search"
            ),
            <.p(^.className := "mr-3", ^.style := js.Dictionary("color" -> "#17206a"))("Uy raqami:"),
            <.input(
              ^.className := "form-control-lg mr-sm-2",
              ^.value     := $.value.houseNumber.toString,
              ^.onChange ==> onChangeHouseNumber,
              ^.`type`               := "number",
              VdomAttr("aria-label") := "Search"
            ),
            <.button(^.className := "btn btn-outline-primary my-2 my-sm-0", ^.onClick --> find)("Qidirish")
          )
        ),
        <.div(^.className := "row")(
          <.div(^.className := "col")(
            <.table(
              ^.id        := "example",
              ^.className := "table table-striped",
              ^.style     := js.Dictionary("width" -> "100%")
            )(
              <.thead(
                <.tr(
                  <.th("Ism"),
                  <.th("Familiya"),
                  <.th("Otasining ismi"),
                  <.th("Tug'ilgan sanasi"),
                  <.th("Jinsi"),
                  <.th("Manzil")
                )
              ),
              <.tbody(
                $.value.families.map { family =>
                  <.tr(
                    <.td(family.firstName.value),
                    <.td(family.lastName.value),
                    <.td(family.fathersName.value),
                    <.td(family.birthday.value),
                    <.td(family.gender.value),
                    <.td(family.livingPlace.value)
                  )
                }: _*
              )
            )
          )
        ).when($.value.families.nonEmpty)
      )

    def render: VdomArray =
      VdomArray(
        <.div(
          info
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
