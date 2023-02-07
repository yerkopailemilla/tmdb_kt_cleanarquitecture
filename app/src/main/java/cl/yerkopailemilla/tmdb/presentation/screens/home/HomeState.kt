package cl.yerkopailemilla.tmdb.presentation.screens.home

import cl.yerkopailemilla.tmdb.domain.models.Movies

data class HomeState(
    val movies: List<Movies> = emptyList(),
    val isLoading: Boolean = false
)
