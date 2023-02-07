package cl.yerkopailemilla.tmdb.presentation.screens.movieDetail.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailMovieImage(image: String?) {
    Box() {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = image,
            contentDescription = "",
        )
    }
}