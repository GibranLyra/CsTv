package com.gibranlyra.cstv.ui.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

internal fun Modifier.addTransparentBackground(roundedCornerShape: Shape = CircleShape) = this
    .background(Color.DarkGray.copy(alpha = 0.6f), roundedCornerShape)
    .clip(roundedCornerShape)