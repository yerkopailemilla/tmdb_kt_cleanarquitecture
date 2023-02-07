package cl.yerkopailemilla.tmdb.utils

import cl.yerkopailemilla.tmdb.di.AppModule
import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import cl.yerkopailemilla.tmdb.domain.models.Movies
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
class FakeRepositoryModule {
    @Provides
    @Singleton
    fun providerMoviesRepository(): MoviesRepository =
        object : MoviesRepository {
            val movies = arrayListOf(
                Movies(1010101, "Content1", "Author1", "Url1"),
                Movies(2020202, "Content2", "Author2", "Url2")
            )

            override fun getMovies(): Flow<Response<List<Movies>>> = flow {
                emit(Response.Loading())
                try {
                    val response = movies
                    emit(Response.Success(response))
                } catch (e: HttpException) {
                    emit(
                        Response.Error(
                            message = "Oops, something went wrong",
                            data = null
                        )
                    )
                } catch (e: IOException) {
                    emit(
                        Response.Error(
                            message = "Couldn't reach server, check your internet connection",
                            data = null
                        )
                    )
                }
            }

            override suspend fun getMovieDetail(idMovie: Int): Response<MovieDetail> {
                TODO("Not yet implemented")
            }
        }
}