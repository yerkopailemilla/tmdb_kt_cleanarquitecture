package cl.yerkopailemilla.tmdb.presentation.screens.movieDetail

import cl.yerkopailemilla.tmdb.R
import cl.yerkopailemilla.tmdb.domain.models.MovieDetail
import cl.yerkopailemilla.tmdb.presentation.components.DefaultTopBar
import cl.yerkopailemilla.tmdb.presentation.screens.movieDetail.components.DetailMovieImage
import cl.yerkopailemilla.tmdb.presentation.ui.theme.Red500
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun MovieDetailScreen(
    navHostController: NavHostController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            DefaultTopBar(title = stringResource(R.string.detalle_pelicula), upAvailable = true, navHostController)
        },
        content = {
            DetailContent(
                movieDetail = state.movieDetail
            )
        },
        bottomBar = {}
    )
}

@Composable
private fun DetailContent(
    movieDetail: MovieDetail?
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Header(movieDetail = movieDetail)
        Divider(
            modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
            startIndent = 20.dp,
            thickness = 1.dp,
            color = Color.DarkGray
        )
        Body(movieDetail = movieDetail)
    }
}

@Composable
private fun Header(
    movieDetail: MovieDetail?
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailMovieImage(image = movieDetail?.image)
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = movieDetail?.name ?: "",
            fontSize = 20.sp,
            color = Red500,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Body(movieDetail: MovieDetail?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = stringResource(R.string.lenguaje),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = movieDetail?.language?.uppercase() ?: "",
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = stringResource(R.string.fecha_estreno),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = movieDetail?.releaseDate ?: "",
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = stringResource(id = R.string.calificacion),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = stringResource(R.string.calificacion_resultado, movieDetail?.average.toString() ?: ""),
            fontSize = 14.sp
        )
        Divider(
            modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp),
            startIndent = 20.dp,
            thickness = 1.dp,
            color = Color.DarkGray
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = stringResource(R.string.resena),
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            text = movieDetail?.description ?: "",
            fontSize = 14.sp
        )
    }
}