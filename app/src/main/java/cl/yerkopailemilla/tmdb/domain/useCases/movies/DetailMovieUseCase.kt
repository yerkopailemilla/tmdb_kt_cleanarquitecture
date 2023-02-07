package cl.yerkopailemilla.tmdb.domain.useCases.movies

import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.repository.MoviesRepository
import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(idMovie: Int): Response<MovieDetail> {
        return repository.getMovieDetail(idMovie)
    }
}