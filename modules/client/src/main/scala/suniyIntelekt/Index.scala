package suniyIntelekt

import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.component.Scala.Component
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{CtorType, _}
import org.scalajs.dom.{HTMLSelectElement, document}
import org.scalajs.dom.html.Div
import suniyIntelekt.selectpicker.SelectPicker

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("index")
class Index {
  val kasalliklar: List[String] = List("infarq", "insult", "arteniar gipertoniya", "yuqori arteniar qon bosomi")
  val simptomlar: List[String] = List("yurak og'rishi", "yurak ishlashi nosozligi", "miyada qon aylanishi buzilishi",
    "bosh og'rig'i", "xushdan ketish", "qonning buzilishi", "qonga infeksiya tushishi", "holsizlik",  "nafas qisishi", "ko'ngil aynishi")
  val normalQonBosimlari: Map[Int, Int] = Map(130 -> 80)
  val yuqoriQonBosimlari: Map[Int, Int] = Map(130 -> 80)
  val pastQonBosimlari: Map[Int, Int] = Map(100 -> 80)

  case class IndexState(
                         simptom1: String = "",
                         simptom2: String = "",
                         simptom3: String = "",
                         simptom4: String = ""
                       )
  type UserComponent = Component[Unit, IndexState, IndexBackend, CtorType.Nullary]

  class IndexBackend($: BackendScope[Unit, IndexState]) {


    def onChangeSimptomName1: SyntheticEvent[HTMLSelectElement] => Callback = e =>
      $.modState(s => s.copy(simptom1 = e.target.value))

    def onChangeSimptomName2: SyntheticEvent[HTMLSelectElement] => Callback = e =>
      $.modState(s => s.copy(simptom2 = e.target.value))

    def onChangeSimptomName3: SyntheticEvent[HTMLSelectElement] => Callback = e =>
      $.modState(s => s.copy(simptom3 = e.target.value))

    def onChangeSimptomName4: SyntheticEvent[HTMLSelectElement] => Callback = e =>
      $.modState(s => s.copy(simptom4 = e.target.value))

    def click(implicit state: IndexState): Callback = {
      val simptoms = List(state.simptom1, state.simptom2, state.simptom3, state.simptom4)
      if (state.simptom1.trim.isEmpty)
        Callback.alert("Iltimos birinchi simptomini tanlang!")
      else if (state.simptom2.trim.isEmpty)
        Callback.alert("Iltimos ikkinchi simptomini tanlang!!")
      else if (state.simptom3.trim.isEmpty)
        Callback.alert("Iltimos uchinchi simptomini tanlang!")
      else if (state.simptom4.trim.isEmpty)
        Callback.alert("Iltimos to'rtinchi simptomini tanlang!!")
      else if(simptoms.contains("yurak og'rishi") && simptoms.contains("yurak ishlashi nosozligi"))
        Callback.alert("Sizda Infarq kasalligi aniqlandi!")
      else if(simptoms.contains("miyada qon aylanishi buzilishi") && simptoms.contains("bosh og'rig'i") && simptoms.contains("xushdan ketish"))
        Callback.alert("Sizda Insult kasalligi aniqlandi!")
      else if(simptoms.contains("qonning buzilishi") && simptoms.contains("qonga infeksiya tushishi"))
        Callback.alert("Sizda Arteniar Gipertoniya kasalligi aniqlandi!")
      else if(simptoms.contains("holsizlik") && simptoms.contains("nafas qisishi") && simptoms.contains("ko'ngil aynishi"))
        Callback.alert("Sizda yuqori arteniar qon bosomi kasalligi aniqlandi!")
      else {
        Callback.alert("Sizda hech qaysi yurak kasalligiga moyillik aniqlanmadi!")
      }
    }


    //    SelectPicker(
//      className = "form-select",
//      onChange = onChangeSimptomName1,
//      isMulti = true,
//      value = state.simptom1)
    def modal(implicit state: IndexState): TagMod = {
      <.div(
        <.div(^.className := "row")(
          <.div(^.className := "offset-3 col-md-6")(
            <.div(^.className := "add-contact px-3 py-3",
              <.h3(^.className := "my-3")("Kasallikni aniqlash!"),
              <.div(^.className := "my-3")(
                <.label(^.className := "form-label")("Simptomlarini tanlang!"),
                <.select(
                  ^.cls := "form-select",
                  ^.onChange ==> onChangeSimptomName1,
                  ^.value := state.simptom1)(
                  <.option(^.selected := true, "Select...")
                    +: simptomlar.map { simptom =>
                    <.option(^.value := simptom)(simptom)
                  }: _*)),
              <.div(^.className := "my-3")(
                <.label(^.className := "form-label")("Simptomlarini tanlang!"),
                <.select(
                  ^.cls := "form-select",
                  ^.onChange ==> onChangeSimptomName2,
                  ^.value := state.simptom2)(
                  <.option(^.selected := true, "Select...")
                    +: simptomlar.map { simptom =>
                    <.option(^.value := simptom)(simptom)
                  }: _*)),
              <.div(^.className := "my-3")(
                <.label(^.className := "form-label")("Simptomlarini tanlang!"),
                <.select(
                  ^.cls := "form-select",
                  ^.onChange ==> onChangeSimptomName3,
                  ^.value := state.simptom3)(
                  <.option(^.selected := true, "Select...")
                    +: simptomlar.map { simptom =>
                    <.option(^.value := simptom)(simptom)
                  }: _*)),
              <.div(^.className := "my-3")(
                <.label(^.className := "form-label")("Simptomlarini tanlang!"),
                <.select(
                  ^.cls := "form-select",
                  ^.onChange ==> onChangeSimptomName4,
                  ^.value := state.simptom4)(
                  <.option(^.selected := true, "Select...")
                    +: simptomlar.map { simptom =>
                    <.option(^.value := simptom)(simptom)
                  }: _*)),
              <.div(^.className := "my-3 row justify-content-center")(
                <.div(^.cls := "col-md-3")(
                <.input(^.className := "btn btn-primary add-contact-btn form-control")(
                  ^.`type` := "button",
                  ^.value := "Yuborish",
                  ^.onClick --> click))))))
      )
    }


    def render(implicit state: IndexState): VdomTagOf[Div] =
      <.div(
        modal
      )
  }

  val IndexApp: UserComponent =
    ScalaComponent
      .builder[Unit]
      .initialState(IndexState())
      .renderBackend[IndexBackend]
      .build

  IndexApp().renderIntoDOM(document.getElementById("index"))
}
