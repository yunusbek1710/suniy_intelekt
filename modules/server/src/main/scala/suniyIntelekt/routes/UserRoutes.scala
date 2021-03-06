package suniyIntelekt.routes


import cats.effect.Async
import cats.implicits._
import suniyIntelekt.domain._
import suniyIntelekt.security.AuthService
import suniyIntelekt.services.UserService
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import org.typelevel.log4cats.Logger
import tsec.authentication._
import tsec.authentication.credentials.CredentialsError

object UserRoutes {
  val prefixPath = "/user"

  def apply[F[_]: Async: Logger](userService: UserService[F])(implicit
    authService: AuthService[F, User]
  ): UserRoutes[F] = new UserRoutes(userService)
}

final class UserRoutes[F[_]: Async](userService: UserService[F])(implicit
  logger: Logger[F],
  authService: AuthService[F, User]
) {

  implicit object dsl extends Http4sDsl[F]
  import dsl._

  private[this] val loginRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
    case req @ POST -> Root / "login" =>
      authService
        .authorizer(req)
        .recoverWith {
          case _: CredentialsError =>
            Forbidden("Email or password isn't correct!")
          case error =>
            logger.error(error)(s"Error occurred while authorization. Error:") >>
              BadRequest("Something went wrong. Please try again!")
        }

    case req @ POST -> Root / "register" =>
      (for {
        userData <- req.as[UserData]
        _        <- logger.debug(s"$userData")
        user     <- userService.create(userData)
        response <- Created(user)
      } yield response)
        .handleErrorWith { err =>
          logger.error(err)("Error occurred while register User. ") >>
            BadRequest("Something went wrong. Please try again!")
        }
  }

  private[this] val privateRoutes: HttpRoutes[F] = authService.securedRoutes {
    case GET -> Root asAuthed user =>
      Ok(user)

    case secureReq @ GET -> Root / "logout" asAuthed _ =>
      authService.discard(secureReq.authenticator)

  }

  val routes: HttpRoutes[F] = loginRoutes <+> privateRoutes
}
