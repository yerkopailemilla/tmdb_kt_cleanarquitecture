package cl.yerkopailemilla.tmdb.domain.models

data class Movies(
    var id: Int = Int.MIN_VALUE,
    var name: String = "",
    var description: String = "",
    var image: String = "",
)
