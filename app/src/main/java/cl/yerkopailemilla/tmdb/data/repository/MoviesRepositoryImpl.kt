package cl.yerkopailemilla.tmdb.data.repository

import cl.yerkopailemilla.tmdb.data.dataSource.MovieApi
import cl.yerkopailemilla.tmdb.data.dataSource.dto.toMovieDetail
import cl.yerkopailemilla.tmdb.data.dataSource.dto.toListMovies
import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import cl.yerkopailemilla.tmdb.domain.models.Movies
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : MoviesRepository {
    override fun getMovies(): Flow<Response<List<Movies>>> = flow {
        emit(Response.Loading())
        try {
            val response = movieApi.getAllMovies().toListMovies()
            emit(Response.Success(response))
        } catch (e: HttpException) {
            emit(
                Response.Error(
                    message = "Algo ha salido mal",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Response.Error(
                    message = "Error de servidor, checkea tu conexión a internet",
                    data = null
                )
            )
        }
    }

    override suspend fun getMovieDetail(idMovie: Int): Response<MovieDetail> {
        val response = try {
            movieApi.getMovieDetail(idMovie)
        } catch (e: Exception) {
            return Response.Error("Error desconocido encontrado")
        }
        return Response.Success(response.toMovieDetail())
    }
}