package suniyIntelekt.db.algebras


case class Algebras[F[_]](
  user: UserAlgebra[F]
)
