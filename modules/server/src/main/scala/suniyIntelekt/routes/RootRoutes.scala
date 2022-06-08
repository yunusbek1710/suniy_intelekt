package suniyIntelekt.routes

import cats.implicits._
import cats.effect.{Async, Sync}
import district.domain.UserInfo
import tsec.authentication.asAuthed
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.typelevel.log4cats.Logger
import suniyIntelekt.security.AuthService
import suniyIntelekt.utils.FileLoader

object RootRoutes {
  def apply[F[_]: Async: Sync: Logger](implicit authService: AuthService[F, UserInfo]): RootRoutes[F] =
    new RootRoutes[F]
}

class RootRoutes[F[_]: Async: Logger](implicit authService: AuthService[F, UserInfo], F: Sync[F]) {
  private[this] val supportedStaticExtensions =
    List(".css", ".png", ".ico", ".js", ".jpg", ".jpeg", ".otf", ".ttf", ".woff2", ".woff", ".svg")

  implicit object dsl extends Http4sDsl[F]; import dsl._

  private[this] val publicRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
    case request @ GET -> Root => FileLoader[F].page("index.html", request)
    case request if supportedStaticExtensions.exists(request.pathInfo.toString.endsWith) =>
      FileLoader[F].assets(request.pathInfo.toString, request)
  }

  private[this] val privateRoutes: HttpRoutes[F] = authService.securedRoutes {
    case secReq @ GET -> Root asAuthed _ =>
    FileLoader[F].page("index.html", secReq.request)
  }

  val routes: HttpRoutes[F] = publicRoutes <+> privateRoutes

}
