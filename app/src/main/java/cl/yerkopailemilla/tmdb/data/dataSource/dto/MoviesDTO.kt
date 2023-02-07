package cl.yerkopailemilla.tmdb.data.dataSource.dto

import cl.yerkopailemilla.tmdb.core.TMDB_IMAGE_URL
import cl.yerkopailemilla.tmdb.domain.models.Movies
import com.google.gson.annotations.SerializedName

data class MoviesDTO (
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieItemDTO> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)

fun MoviesDTO.toListMovies(): List<Movies> {
    val resultEntries = results.mapIndexed { _, entries ->
        Movies(
            id = entries.id!!,
            name = entries.originalTitle!!,
            description = entries.overview!!,
            image = TMDB_IMAGE_URL+entries.posterPath!!
        )
    }
    return resultEntries
}