package com.gibranlyra.cstv.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.gibranlyra.cstv.R

@Composable
internal fun CsTvAsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter = painterResource(id = R.drawable.ic_launcher_foreground),
    contentScale: ContentScale = ContentScale.Fit,
) {
    if (imageUrl.isNotEmpty()) {
        val request = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)

        AsyncImage(
            modifier = modifier,
            model = request.build(),
            contentDescription = contentDescription,
            placeholder = placeholder,
            contentScale = contentScale,
        )
    } else {
        Image(modifier = modifier, painter = placeholder, contentDescription = contentDescription)
    }
}
