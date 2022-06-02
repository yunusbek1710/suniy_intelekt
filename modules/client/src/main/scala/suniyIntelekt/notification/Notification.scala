package suniyIntelekt.notification

import suniyIntelekt.notification.NotificationStyle._
import io.udash.wrappers.jquery.jQ
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.facade.SyntheticEvent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.HTMLElement
import org.scalajs.dom.html.Div
import scalacss.ScalaCssReact._

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.scalajs.js.timers._
import scala.util.Random

object Notification {
  sealed trait NotificationType

  case object Success extends NotificationType

  case object Warn extends NotificationType

  case object Fail extends NotificationType

  case object Info extends NotificationType
  case class Toast(`type`: NotificationType, icon: String, message: String, title: String)

  case class ToastState(toasts: Map[String, Toast] = Map.empty, duration: FiniteDuration)
  class Backend($ : BackendScope[Int, ToastState]) {
    def genToastrId: String = Random.alphanumeric.take(5).mkString

    def toastMsg(`type`: NotificationType, icon: String, message: String, title: String): Callback = {
      val id       = genToastrId
      val newToast = Toast(`type`, icon, message, title)
      $.modState { s =>
        deleteToastAfterTimeout(id, s.duration)
        s.copy(toasts = s.toasts + (id -> newToast))
      }
    }

    def deleteToastAfterTimeout(id: String, duration: FiniteDuration): SetTimeoutHandle =
      setTimeout(duration) {
        $.modState(s => s.copy(toasts = s.toasts - id)).runNow()
      }

    def deleteToastAfterClick(): SyntheticEvent[HTMLElement] => Callback = e =>
      Callback(jQ(e.currentTarget).parent("section").parent().fadeOut(500))

    def render(state: ToastState): VdomTagOf[Div] =
      <.div(notificationBox)(
        state.toasts.values.zipWithIndex
          .toVdomArray { case (toast, index) =>
            <.div(^.key := s"toast$index", notification, typeStyle(toast.`type`))(
              <.section(wrapper)(
                <.div(<.i(icon, ^.cls := "fa " + toast.icon), <.span(title)(toast.title)),
                <.i(close, ^.cls := "fa fa-times", ^.onClick ==> deleteToastAfterClick())
              ) +: toast.message.split('\n').map(str => <.span(message)(str)).toList: _*
            )
          }
          .when(state.toasts.nonEmpty)
      )
  }

  private val component =
    ScalaComponent
      .builder[Int]
      .initialStateFromProps(p => ToastState(duration = p seconds))
      .renderBackend[Backend]
      .build

  private val toastRef = Ref.toScalaComponent(component)

  private def toast(`type`: NotificationType, icon: String, message: String, title: String): CallbackTo[Unit] =
    toastRef
      .map(_.backend.toastMsg(`type`, icon, message, title))
      .get
      .map(_.getOrElse(Callback.empty))
      .flatten

  def render(duration: Int = 6): Unmounted[Int, ToastState, Backend] = toastRef.component(duration)

  def warning(message: String): Callback =
    toast(Warn, "fa-exclamation-circle", message, "Notification")

  def info(message: String): Callback =
    toast(Info, "fa-info-circle", message, "Information")

  def success(message: String): Callback =
    toast(Success, "fa-check", message, "Successful")

  def error(message: String): Callback =
    toast(Fail, "fa-exclamation-triangle", message, "Error")

}
