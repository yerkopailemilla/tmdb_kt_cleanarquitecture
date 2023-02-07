package cl.yerkopailemilla.tmdb.domain.useCases.movies

data class MoviesUseCases(
    val getMovies: GetMoviesUseCase,
    val detailMovie: DetailMovieUseCase
)