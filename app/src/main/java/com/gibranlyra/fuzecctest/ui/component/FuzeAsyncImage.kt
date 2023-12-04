package com.gibranlyra.fuzecctest.ui.component

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
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.util.BlurTransformation

@Composable
internal fun FuzeAsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: Painter = painterResource(id = R.drawable.ic_launcher_foreground),
    contentScale: ContentScale = ContentScale.Fit,
    blurImage: Boolean = false,
) {
    if (imageUrl.isNotEmpty()) {
        val request = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .apply {
                if (blurImage) {
                    transformations(BlurTransformation(scale = 1f, radius = 4))
                }
            }

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
