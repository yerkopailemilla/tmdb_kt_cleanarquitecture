package cl.yerkopailemilla.tmdb.presentation.screens.movieDetail

import cl.yerkopailemilla.tmdb.domain.models.MovieDetail

data class MovieDetailState(
    val movieDetail: MovieDetail? = null,
    val isLoading: Boolean = false
)