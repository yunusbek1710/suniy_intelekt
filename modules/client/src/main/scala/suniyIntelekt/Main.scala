package suniyIntelekt

import org.scalajs.dom.document
import suniyIntelekt.notification.CssSettings._
import suniyIntelekt.notification.NotificationStyle
import scalacss.toStyleSheetInlineJsOps

object Main extends App {
  NotificationStyle.addToDocument()
  TopLevelComponent().renderIntoDOM(document.getElementById("index"))
}
