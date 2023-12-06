package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
internal fun FuzeText(
    text: String,
    modifier: Modifier = Modifier,
    style: FuzeTextStyle = FuzeTextStyle.BASE,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    styleOverride: TextStyle = TextStyle(),
    textAlign: TextAlign? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        overflow = overflow,
        maxLines = maxLines,
        textAlign = textAlign,
        style = style.toTextStyle() + styleOverride,
    )
}

internal enum class FuzeTextStyle {
    LARGE,
    LARGE_SUBTLE,
    BASE,
    BASE_SUBTLE,
    SMALL,
    SMALL_SUBTLE,
    X_SMALL,
    X_SMALL_SUBTLE,
}

@Composable
internal fun FuzeTextStyle.toTextStyle(): TextStyle =
    when (this) {
        FuzeTextStyle.LARGE -> TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 32.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.LARGE_SUBTLE -> TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 28.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.BASE -> TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 24.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.BASE_SUBTLE -> TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.SMALL -> TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 24.sp,
            letterSpacing = (-0.2).sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.SMALL_SUBTLE -> TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 20.sp,
            letterSpacing = (-0.2).sp,
            color = MaterialTheme.colorScheme.onPrimary
        )

        FuzeTextStyle.X_SMALL -> TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 16.sp,
        )

        FuzeTextStyle.X_SMALL_SUBTLE -> TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 10.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

