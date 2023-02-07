package cl.yerkopailemilla.tmdb.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.yerkopailemilla.tmdb.domain.models.Movies
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    item: Movies,
    onItemClicked: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item.id.toInt()) }
            .padding(start = 6.dp, top = 12.dp, bottom = 12.dp)
    ) {
        MovieImageContainer(modifier = Modifier.size(70.dp)) {
            MovieImage(item)
        }
        Spacer(Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.caption
                )
            }
        }
        Divider(modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
private fun MovieImage(item: Movies) {
    Box {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .size(Size.ORIGINAL)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun MovieImageContainer(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), RoundedCornerShape(4.dp)) {
       content()
    }
}