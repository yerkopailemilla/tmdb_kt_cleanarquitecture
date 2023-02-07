package cl.yerkopailemilla.tmdb.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import cl.yerkopailemilla.tmdb.R
import cl.yerkopailemilla.tmdb.domain.models.Movies
import cl.yerkopailemilla.tmdb.presentation.components.DefaultTopBar
import cl.yerkopailemilla.tmdb.presentation.components.ProgressBar
import cl.yerkopailemilla.tmdb.presentation.navigation.MainDirections
import cl.yerkopailemilla.tmdb.presentation.screens.home.components.MovieItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DefaultTopBar(title = stringResource(R.string.peliculas_populares))
        },
        bottomBar = {}
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            onItemClicked = {
                navHostController.navigate(
                    route = MainDirections.MovieDetail.passMovieId(it)
                )
            },
            isLoading = state.isLoading,
            movies = state.movies
        )
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    movies: List<Movies> = emptyList()
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 6.dp),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(movies.size) { index ->
                    MovieItem(
                        modifier = Modifier.fillMaxWidth(),
                        item = movies[index],
                        onItemClicked = { onItemClicked(it) }
                    )
                }
            }
        )
        if (isLoading) ProgressBar()
    }
}