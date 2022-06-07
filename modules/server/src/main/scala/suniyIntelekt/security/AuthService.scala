package suniyIntelekt.security

import cats.effect._
import cats.implicits._
import suniyIntelekt.domain.Credentials
import suniyIntelekt.domain.custom.refinements.EmailAddress
import suniyIntelekt.implicits.PartOps
import suniyIntelekt.security.AuthHelper._
import suniyIntelekt.services.IdentityService
import suniyIntelekt.services.redis.RedisClient
import eu.timepit.refined.auto.autoUnwrap
import io.circe.refined._
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.dsl.Http4sDsl
import org.http4s.headers.Location
import org.http4s.multipart.Multipart
import org.http4s.{HttpDate, HttpRoutes, Request, Response, ResponseCookie, Status, Uri}
import tsec.authentication._
import tsec.authentication.credentials.RawCredentials
import tsec.cipher.symmetric.jca.{AES128GCM, SecretKey}
import tsec.cipher.symmetric.{AADEncryptor, IvGen}
import tsec.common.SecureRandomId

import java.time.Instant
import java.util.UUID
import scala.concurrent.duration.DurationInt

abstract class AuthService[F[_]: Sync, U] {
  private[this] val cachedUnauthorized: Response[F] = Response[F](Status.Unauthorized)
  protected val defaultNotAuthenticated: OnNotAuthenticated[F] = _ => Sync[F].pure(cachedUnauthorized)

  def authorizer(request: Multipart[F])(implicit dsl: Http4sDsl[F]): F[Response[F]]

  def authorizer(request: Request[F])(implicit dsl: Http4sDsl[F]): F[Response[F]]

  def securedRoutesWithToken(
    pf: TokenSecHttpRoutes[F, U],
    onNotAuthenticated: OnNotAuthenticated[F] = defaultNotAuthenticated
  ): HttpRoutes[F]

  def securedRoutes(
    pf: SecHttpRoutes[F, U],
    onNotAuthenticated: OnNotAuthenticated[F] = defaultNotAuthenticated
  ): HttpRoutes[F]

  def discard(authenticator: AuthEncryptedCookie[AES128GCM, EmailAddress])(implicit dsl: Http4sDsl[F]): F[Response[F]]
}

object AuthService {
  def apply[F[_]: Async, U](
    identityService: IdentityService[F, U],
    key: SecretKey[AES128GCM]
  )(implicit F: Sync[F], redisClient: RedisClient[F]): F[AuthService[F, U]] =
    F.delay(
      new LiveAuthService[F, U](identityService, key)
    )

  final class LiveAuthService[F[_]: Async, U] (
    identityService: IdentityService[F, U],
    key: SecretKey[AES128GCM]
  )(implicit F: Sync[F], redisClient: RedisClient[F])
    extends AuthService[F, U] {

    implicit val encryptor: AADEncryptor[F, AES128GCM, SecretKey] = AES128GCM.genEncryptor[F]
    implicit val gcmStrategy: IvGen[F, AES128GCM]                 = AES128GCM.defaultIvStrategy[F]

    private[this] val bearerTokenStore =
      redisClient.dummyBackingStore[SecureRandomId, TSecBearerToken[EmailAddress]](s => SecureRandomId.coerce(s.id))

    private[this] val settings: TSecTokenSettings =
      TSecTokenSettings(
        expiryDuration = 8.hours,
        maxIdle = 30.minutes.some
      )

    private[this] val cookieSetting: TSecCookieSettings =
      TSecCookieSettings(
        secure = true,
        expiryDuration = 8.hours,
        maxIdle = 30.minutes.some
      )

    private[this] def bearerTokenAuth: BearerTokenAuthenticator[F, EmailAddress, U] =
      BearerTokenAuthenticator(
        bearerTokenStore,
        identityService,
        settings
      )

    private[this] def stateless: StatelessECAuthenticator[F, EmailAddress, U, AES128GCM] =
      EncryptedCookieAuthenticator.stateless(
        cookieSetting,
        identityService,
        key
      )

    private[this] def session(seconds: Long) =
      ResponseCookie(
        "ajs_anonymous_id",
        UUID.randomUUID().toString,
        Some(HttpDate.unsafeFromInstant(Instant.now().plusSeconds(seconds))),
        path = "/".some,
        secure = true,
        httpOnly = false
      )

    private[this] def authWithToken: TokenSecReqHandler[F, U] = SecuredRequestHandler(bearerTokenAuth)

    private[this] def auth: SecReqHandler[F, U] = SecuredRequestHandler(stateless)

    private[this] def verify(Credentials: Credentials): F[Boolean] =
      identityService.credentialStore.isAuthenticated(RawCredentials(Credentials.email, Credentials.password))

    private[this] def createSession(credentials: Credentials): F[Response[F]] = {
      auth.authenticator
        .create(credentials.email)
        .map(auth.authenticator.embed(Response(Status.NoContent).addCookie(session(30 * 60)), _))
    }

    override def authorizer(request: Multipart[F])(implicit dsl: Http4sDsl[F]): F[Response[F]] = {
      import dsl._
      for {
        credentials <- request.parts.convert[Credentials]
        isAuthed    <- verify(credentials)
        response <-
          if (isAuthed)
            createSession(credentials)
          else
            Forbidden("Email or password isn't correct")
      } yield response
    }

    override def authorizer(request: Request[F])(implicit dsl: Http4sDsl[F]): F[Response[F]] = {
      import dsl._
      for {
        credentials <- request.as[Credentials]
        isAuthed    <- verify(credentials)
        response <-
          if (isAuthed)
            createSession(credentials)
          else
            Forbidden("Email or password isn't correct")
      } yield response
    }

    override def securedRoutesWithToken(
                                         pf: TokenSecHttpRoutes[F, U],
                                         onNotAuthenticated: OnNotAuthenticated[F]
                                       ): HttpRoutes[F] =
      authWithToken.liftService(TSecAuthService(pf), onNotAuthenticated.orElse(defaultNotAuthenticated))

    override def securedRoutes(pf: SecHttpRoutes[F, U], onNotAuthenticated: OnNotAuthenticated[F]): HttpRoutes[F] =
      auth.liftService(TSecAuthService(pf).map(_.addCookie(session(30 * 60))),
        onNotAuthenticated.orElse(defaultNotAuthenticated).map(_.map(_.addCookie(session(0))))
      )

    override def discard(
                          authenticator: AuthEncryptedCookie[AES128GCM, EmailAddress]
                        )(implicit dsl: Http4sDsl[F]): F[Response[F]] = {
      import dsl._
      auth.authenticator.discard(authenticator).flatMap { cookie =>
        SeeOther(Location(Uri.unsafeFromString("/"))).map(_.addCookie(cookie.toCookie).addCookie(session(0)))
      }
    }
  }

}

