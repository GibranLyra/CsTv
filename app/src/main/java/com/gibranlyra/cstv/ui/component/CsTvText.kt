package com.gibranlyra.cstv.ui.component

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
internal fun CsTvText(
    text: String,
    modifier: Modifier = Modifier,
    style: CsTvTextStyle = CsTvTextStyle.BASE,
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

internal enum class CsTvTextStyle {
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
internal fun CsTvTextStyle.toTextStyle(): TextStyle =
    when (this) {
        CsTvTextStyle.LARGE ->
            TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.LARGE_SUBTLE ->
            TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.BASE ->
            TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.BASE_SUBTLE ->
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.SMALL ->
            TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 24.sp,
                letterSpacing = (-0.2).sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.SMALL_SUBTLE ->
            TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp,
                letterSpacing = (-0.2).sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

        CsTvTextStyle.X_SMALL ->
            TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 16.sp,
            )

        CsTvTextStyle.X_SMALL_SUBTLE ->
            TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 10.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )
    }
