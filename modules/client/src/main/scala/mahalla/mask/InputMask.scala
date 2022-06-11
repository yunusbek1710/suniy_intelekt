package mahalla.mask

import io.kinoplan.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import io.kinoplan.scalajs.react.material.ui.core.MuiDialogExtensions
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all.onChange.Event

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object InputMask extends ReactBridgeComponent with MuiDialogExtensions {
  override protected lazy val componentValue: js.Function = RawComponent

  type OnChange = js.Function1[Event, Unit]
  @JSImport("react-input-mask", JSImport.Default)
  @js.native
  object RawComponent extends js.Function

  def apply(
    alwaysShowMask: js.UndefOr[Boolean] = false,
    className: js.UndefOr[String] = js.undefined,
    value: js.UndefOr[String] = js.undefined,
    `type`: js.UndefOr[String] = js.undefined,
    id: js.UndefOr[String] = js.undefined,
    name: js.UndefOr[String] = js.undefined,
    placeholder: js.UndefOr[String] = js.undefined,
    mask: js.UndefOr[String] = js.undefined,
    maskChar: js.UndefOr[String] = "_",
    onChange: Event => Callback = _ => Callback.empty
  ): WithProps = auto
}
