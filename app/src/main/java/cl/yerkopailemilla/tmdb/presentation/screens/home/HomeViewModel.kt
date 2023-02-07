package cl.yerkopailemilla.tmdb.presentation.screens.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.yerkopailemilla.tmdb.domain.models.Response
import cl.yerkopailemilla.tmdb.domain.useCases.movies.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesUseCases: MoviesUseCases): ViewModel() {
    var state by mutableStateOf(HomeState(isLoading = true))
        private set

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAllMovies()
    }

    fun getAllMovies() {
        viewModelScope.launch {
            moviesUseCases.getMovies().onEach {  result ->
                when (result) {
                    is Response.Success -> {
                        state = state.copy(
                            movies = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Response.Error -> {
                        state = state.copy(
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(
                            result.message ?: "Unknown error"
                        ))
                    }
                    is Response.Loading -> {
                        state = state.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String): UIEvent()
    }
}