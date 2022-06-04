package mahalla.selectpicker

import japgolly.scalajs.react.component.Js.{Component, RawMounted, UnmountedWithRawType}
import japgolly.scalajs.react.{CtorType, _}

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport

object SelectPicker {

  type OnChange        = js.Function1[js.Array[Option], Unit]
  type SelectComponent = Component[Props, Null, CtorType.Props]
  @JSImport("react-select", JSImport.Default)
  @js.native
  object SelectPickerComponent extends js.Object

  @js.native
  trait Option extends js.Object {
    var value: String = js.native
    var label: String = js.native
  }

  object Option {
    def apply(value: String, label: String): Option =
      js.Dynamic.literal(value = value, label = label).asInstanceOf[Option]
  }

  @js.native
  trait Props extends js.Object {
    var id: js.UndefOr[String]                 = js.undefined
    var className: js.UndefOr[String]          = js.undefined
    var name: js.UndefOr[String]               = js.undefined
    var closeMenuOnSelect: js.UndefOr[Boolean] = js.undefined
    var inputValue: js.UndefOr[String]         = js.undefined
    var isDisabled: js.UndefOr[Boolean]        = js.undefined
    var isLoading: js.UndefOr[Boolean]         = js.undefined
    var isMulti: js.UndefOr[Boolean]           = js.undefined
    var isRtl: js.UndefOr[Boolean]             = js.undefined
    var isSearchable: js.UndefOr[Boolean]      = js.undefined
    var onChange: OnChange                     = js.native
    var options: js.UndefOr[js.Array[Option]]  = js.undefined
    var placeholder: js.UndefOr[String]        = js.undefined
    var value: js.UndefOr[String]              = js.undefined
  }

  def props(
    id: js.UndefOr[String],
    className: js.UndefOr[String],
    name: js.UndefOr[String],
    value: js.UndefOr[String],
    closeMenuOnSelect: js.UndefOr[Boolean],
    inputValue: js.UndefOr[String],
    isDisabled: js.UndefOr[Boolean],
    isLoading: js.UndefOr[Boolean],
    isMulti: js.UndefOr[Boolean],
    isRtl: js.UndefOr[Boolean],
    isSearchable: js.UndefOr[Boolean],
    onChange: js.Array[Option] => Callback,
    options: js.UndefOr[js.Array[Option]],
    placeholder: js.UndefOr[String]
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.id = id
    p.className = className
    p.name = name
    p.value = value
    p.closeMenuOnSelect = closeMenuOnSelect
    p.inputValue = inputValue
    p.isDisabled = isDisabled
    p.isLoading = isLoading
    p.isMulti = isMulti
    p.isRtl = isRtl
    p.isSearchable = isSearchable
    p.onChange = e => onChange(e).runNow()
    p.options = options
    p.placeholder = placeholder
    p
  }

  val component: SelectComponent = JsComponent[Props, Children.None, Null](SelectPickerComponent)

  final type UnmountedRaw = UnmountedWithRawType[Props, Null, RawMounted[Props, Null]]
  def apply(
    id: js.UndefOr[String] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    name: js.UndefOr[String] = js.undefined,
    value: js.UndefOr[String] = js.undefined,
    inputValue: js.UndefOr[String] = js.undefined,
    closeMenuOnSelect: Boolean = true,
    isDisabled: Boolean = false,
    isLoading: Boolean = false,
    isMulti: Boolean = false,
    isRtl: Boolean = false,
    isSearchable: Boolean = false,
    onChange: js.Array[Option] => Callback = _ => Callback.empty,
    options: List[Option] = Nil,
    placeholder: String = "Select..."
  ): UnmountedRaw = {
    component(
      props(
        id,
        className,
        name,
        value,
        closeMenuOnSelect = !isMulti,
        inputValue,
        isDisabled,
        isLoading,
        isMulti,
        isRtl,
        isSearchable,
        onChange,
        options.toJSArray,
        placeholder))
  }
}
