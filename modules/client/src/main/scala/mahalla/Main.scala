package mahalla

import mahalla.notification.NotificationStyle
import org.scalajs.dom.document
import mahalla.notification.CssSettings._
import scalacss.toStyleSheetInlineJsOps

object Main extends App {
  NotificationStyle.addToDocument()
  TopLevelComponent().renderIntoDOM(document.getElementById("index"))
}
