package suniyIntelekt.notification

import scalacss.internal.Keyframes
import suniyIntelekt.notification.CssSettings._
import suniyIntelekt.notification.Notification._
import scalacss.defaults.Exports.StyleSheet

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object NotificationStyle extends StyleSheet.Inline {
  import dsl._

  val from: StyleA = keyframe(
    opacity(0),
    right(-200 px)
  )

  val to: StyleA = keyframe(
    opacity(1),
    bottom(20 px)
  )

  val creating: Keyframes = keyframes(
    (0%%) -> from,
    (100%%) -> to
  )

  val notificationBox: StyleA = style(
    position.fixed,
    bottom(0 px),
    right(0.px),
    padding(10 px),
    zIndex(9999)
  )

  val notification: StyleA = style(
    transition := "all 0.5s cubic-bezier(0.09, 0.71, 0.35, 0.94) 0s",
    animationName(creating),
    animationDuration(1 seconds),
    position.relative,
    top(0 px),
    right(10 px),
    fontSize(15 px),
    letterSpacing(0.3 px),
    padding(10 px, 15 px),
    borderRadius(5 px),
    boxShadow := "0px 0px 16px rgba(17, 17, 26, 0.1)",
    minHeight(65 px),
    color :=! c"#FFF",
    width(400 px),
    margin(5 px, 0 px)
  )

  val wrapper: StyleA = style(
    display.flex,
    justifyContent.spaceBetween
  )

  val title: StyleA = style(
    margin(0 px, 0 px, 0 px, 10 px),
    fontSize(18 px),
    fontWeight.bold
  )

  val message: StyleA = style(
    display.block,
    margin(0.5 rem, 0 px)
  )

  val close: StyleA = style(
    backgroundColor :=! c"#ffffff8c",
    width(20 px),
    height(20 px),
    display.flex,
    justifyContent.center,
    alignItems.center,
    borderRadius(20 px),
    float.right,
    cursor.pointer,
    &.hover(
      backgroundColor :=! c"#FFF",
      color :=! c"#F00"
    )
  )

  val icon: StyleA = style(
    fontSize(20 px)
  )

  val success: StyleA = style(
    backgroundColor :=! c"#39DA8A",
    boxShadow := "2px 3px 10px 0 rgba(57, 218, 138, .4)"
  )

  val warn: StyleA = style(
    backgroundColor :=! c"#FDAC41",
    boxShadow := "2px 3px 10px 0 rgba(253, 172, 65, .4)"
  )

  val info: StyleA = style(
    backgroundColor :=! c"#00CFDD",
    boxShadow := "2px 3px 10px 0 rgba(0, 207, 221, .4)"
  )

  val fail: StyleA = style(
    backgroundColor :=! c"#FF5B5C"
  )

  val typeStyle: NotificationType => StyleA = {
    case Success => success
    case Warn    => warn
    case Fail    => fail
    case Info    => info
  }
}