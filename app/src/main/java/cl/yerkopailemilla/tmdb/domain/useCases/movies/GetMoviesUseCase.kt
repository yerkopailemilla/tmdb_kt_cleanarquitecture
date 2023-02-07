package cl.yerkopailemilla.tmdb.domain.useCases.movies

import cl.yerkopailemilla.tmdb.domain.models.Movies
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {
    operator fun invoke(): Flow<Response<List<Movies>>> {
        return repository.getMovies()
    }
}