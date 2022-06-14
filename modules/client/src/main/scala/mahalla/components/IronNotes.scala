package mahalla.components

import district.api.Urls.AdminData
import district.domain.Person
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.hooks.Hooks
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, ScalaFnComponent}
import mahalla.AjaxImplicits
import mahalla.TopLevelComponent.AppPage

import scala.scalajs.js

object IronNotes extends AjaxImplicits {

  case class State(
    busyLoader: Boolean = false,
    personalInfos: List[Person] = Nil
  )

  class Backend($ : Hooks.UseState[State]) {

    def infos: TagMod =
      <.div(^.className := "container mt-5 mb-5")(
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
                  <.th("Manzil"),
                  <.th("Ko'cha nomi"),
                  <.th("Oilaviy ahvoli"),
                  <.th("Salomatligi"),
                  <.th("Yoshlar daftari a'zosi"),
                  <.th("Temir daftari a'zosi")
                )
              ),
              <.tbody(
                $.value.personalInfos.filter(_.ironNote.value == "ha").map { family =>
                  <.tr(
                    <.td(family.firstName.value),
                    <.td(family.lastName.value),
                    <.td(family.fathersName.value),
                    <.td(family.birthday.value),
                    <.td(family.gender.value),
                    <.td(family.livingPlace.value),
                    <.td(family.street.value),
                    <.td(family.familyStatus.value),
                    <.td(family.healthStatus.value),
                    <.td(family.youthNote.value),
                    <.td(family.ironNote.value)
                  )
                }: _*
              )
            )
          )
        )
      )

    def render: VdomArray =
      VdomArray(
        <.div(
          infos
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
