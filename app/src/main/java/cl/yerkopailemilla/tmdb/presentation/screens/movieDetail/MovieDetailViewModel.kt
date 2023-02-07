package cl.yerkopailemilla.tmdb.presentation.screens.movieDetail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.useCases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(MovieDetailState())
        private set

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        savedStateHandle.get<Int>("idMovie")?.let { movieId ->
            viewModelScope.launch {
                moviesUseCases.detailMovie(movieId).also { response ->
                    when (response) {
                        is Response.Success -> {
                            state = state.copy(
                                movieDetail = response.data,
                                isLoading = false
                            )
                        }
                        is Response.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                        }
                        is Response.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
    }
}