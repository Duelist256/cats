package alleycats

import cats.SemigroupK
import simulacrum.typeclass

@typeclass trait ConsK[F[_]] {
  def cons[A](hd: A, tl: F[A]): F[A]
}

object ConsK {
  implicit def pureSemigroupKIsConsK[F[_]](implicit p: Pure[F], s: SemigroupK[F]): ConsK[F] =
    new ConsK[F] {
      def cons[A](hd: A, tl: F[A]): F[A] = s.combineK(p.pure(hd), tl)
    }
}
