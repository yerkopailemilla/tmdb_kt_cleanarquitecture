package cl.yerkopailemilla.tmdb.domain.repository

import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import cl.yerkopailemilla.tmdb.domain.models.Response
import kotlinx.coroutines.flow.Flow
import cl.yerkopailemilla.tmdb.domain.models.Movies

interface MoviesRepository {
    fun getMovies(): Flow<Response<List<Movies>>>
    suspend fun getMovieDetail(idMovie: Int): Response<MovieDetail>
}