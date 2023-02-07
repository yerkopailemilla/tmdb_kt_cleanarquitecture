package cl.yerkopailemilla.tmdb.di

import cl.yerkopailemilla.tmdb.core.BASE_URL
import cl.yerkopailemilla.tmdb.data.dataSource.MovieApi
import cl.yerkopailemilla.tmdb.data.repository.MoviesRepositoryImpl
import cl.yerkopailemilla.tmdb.domain.repository.MoviesRepository
import cl.yerkopailemilla.tmdb.domain.useCases.movies.DetailMovieUseCase
import cl.yerkopailemilla.tmdb.domain.useCases.movies.GetMoviesUseCase
import cl.yerkopailemilla.tmdb.domain.useCases.movies.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    fun provideMoviesRepository(impl: MoviesRepositoryImpl): MoviesRepository = impl

    @Provides
    fun provideMoviesUseCases(repository: MoviesRepository) = MoviesUseCases(
        getMovies = GetMoviesUseCase(repository),
        detailMovie = DetailMovieUseCase(repository)
    )
}