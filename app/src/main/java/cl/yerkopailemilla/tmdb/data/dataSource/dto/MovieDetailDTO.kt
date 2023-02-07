package cl.yerkopailemilla.tmdb.data.dataSource.dto

import cl.yerkopailemilla.tmdb.core.TMDB_IMAGE_URL
import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import com.google.gson.annotations.SerializedName

data class MovieDetailDTO(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("budget") var budget: Int? = null,
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("imdb_id") var imdbId: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("revenue") var revenue: Int? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
)

fun MovieDetailDTO.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id!!,
        name = originalTitle!!,
        description = overview!!,
        image = TMDB_IMAGE_URL+posterPath!!,
        language = originalLanguage!!,
        releaseDate = releaseDate!!,
        average = voteAverage!!
    )
}