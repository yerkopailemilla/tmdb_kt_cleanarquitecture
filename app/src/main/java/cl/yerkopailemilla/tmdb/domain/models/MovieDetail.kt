package cl.yerkopailemilla.tmdb.domain.models

data class MovieDetail(
    var id: Int = Int.MIN_VALUE,
    var name: String = "",
    var description: String = "",
    var image: String = "",
    var language: String = "",
    var releaseDate: String = "",
    var average: Double = Double.MIN_VALUE
)
