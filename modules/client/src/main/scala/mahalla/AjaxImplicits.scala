package mahalla

import district.api.Urls
import io.circe.{Decoder, Encoder}
import japgolly.scalajs.react.callback.Callback
import japgolly.scalajs.react.extra.Ajax
import district.scalajs.JsonUtils._
import japgolly.scalajs.react.extra.internal.AjaxException
import org.scalajs.dom.{FormData, XMLHttpRequest, document, window}
import japgolly.scalajs.react.extra.Ajax.Step2
import mahalla.notification.Notification

trait AjaxImplicits {
  val TokenName = "Authorization"

  case class Token(name: String, value: String)

  def onError(exception: AjaxException): Callback = exception match {
    case error if error.xhr.status == 401 =>
      Callback.empty.map { _ =>
        window.location.href = Urls.Common.logout
      }
    case error if (error.xhr.status == 200 || error.xhr.status == 400) && error.xhr.responseText.nonEmpty =>
      Notification.error(error.xhr.responseText)
    case _ =>
      Notification.error("Something went wrong. Please try again!")
  }

  def encrypt: String => String = window.btoa

  def decrypt: String => String = window.atob

  private def setToken(xhr: XMLHttpRequest): Unit =
    getToken(xhr).foreach { token =>
      xhr.setRequestHeader(TokenName, token)
    }

  private def setCookie(implicit xhr: XMLHttpRequest): Callback =
    getToken(xhr).fold(Callback.empty)(token => Callback(document.cookie = s"$TokenName=$token;path=/"))

  private def getToken(implicit xhr: XMLHttpRequest): Option[String] =
    Option(xhr.getResponseHeader(TokenName)).orElse(
      document.cookie.split(";").find(_.startsWith(TokenName)).flatMap(_.split("=").lastOption)
    )

  private def hasToken(implicit xhr: XMLHttpRequest): Boolean =
    Option(xhr.getResponseHeader(TokenName)).exists(_.trim.nonEmpty)

  def post[T: Encoder](url: String, body: T): Step2 =
    Ajax("POST", url).setRequestContentTypeJsonUtf8
      .and(setToken)
      .send(toJsonString(body))

  def put(url: String, body: FormData): Step2 =
    Ajax("PUT", url)
      .send(body)

  def get(url: String): Step2 = Ajax("GET", url).setRequestContentTypeJsonUtf8.and(setToken).send

  implicit class Step2Ops(val step2: Step2) {
    def isSuccessFull: XMLHttpRequest => Boolean = xhr =>
      (xhr.status >= 200 && xhr.status < 300 && xhr.responseText.nonEmpty &&
        xhr.getResponseHeader("Content-Type") == "application/json") || xhr.status == 204 || xhr.status == 201

    final def fail(onFailure: AjaxException => Callback): Step2 =
      step2.onComplete(xhr => Callback.unless(isSuccessFull(xhr))(onFailure(AjaxException(xhr))))

    final def done[T: Decoder](onSuccess: T => Callback): Step2 =
      step2.onComplete { implicit xhr =>
        Callback.when(hasToken)(setCookie) >>
          Callback.when(isSuccessFull(xhr))(onSuccess(fromJson[T](xhr.responseText)))
      }

    final def doneWithoutContent(onSuccess: => Callback): Step2 =
      step2.onComplete { implicit xhr =>
        Callback.when(hasToken)(setCookie) >>
          Callback.when(isSuccessFull(xhr))(onSuccess)
      }
  }

}

