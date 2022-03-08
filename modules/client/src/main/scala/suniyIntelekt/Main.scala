package suniyIntelekt

import org.scalajs.dom.document


object Main extends App {
  if (document.getElementById("index") != null) new Index()
}
