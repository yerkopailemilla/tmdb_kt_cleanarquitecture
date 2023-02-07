package cl.yerkopailemilla.tmdb.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import cl.yerkopailemilla.tmdb.presentation.screens.home.HomeScreen
import cl.yerkopailemilla.tmdb.presentation.screens.movieDetail.MovieDetailScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MAIN,
        startDestination = MainDirections.Home.route
    ) {
        composable(route = MainDirections.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = MainDirections.MovieDetail.route,
            arguments = listOf(navArgument("idMovie"){
                type = NavType.IntType
            })
        ) {
            MovieDetailScreen(navController)
        }
    }
}

sealed class MainDirections(val route: String) {
    object Home: MainDirections("home")
    object MovieDetail: MainDirections("movie/{idMovie}") {
        fun passMovieId(idMovie: Int) = "movie/$idMovie"
    }
}