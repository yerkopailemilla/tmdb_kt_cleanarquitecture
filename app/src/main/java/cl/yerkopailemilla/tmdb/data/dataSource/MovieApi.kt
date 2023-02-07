package cl.yerkopailemilla.tmdb.data.dataSource

import cl.yerkopailemilla.tmdb.data.dataSource.dto.MoviesDTO
import cl.yerkopailemilla.tmdb.data.dataSource.dto.MovieDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("3/movie/popular?api_key=34738023d27013e6d1b995443764da44")
    suspend fun getAllMovies(): MoviesDTO

    @GET("/3/movie/{idMovie}?api_key=34738023d27013e6d1b995443764da44")
    suspend fun getMovieDetail(@Path("idMovie") idMovie: Int): MovieDetailDTO
}