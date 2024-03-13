package com.gibranlyra.cstv.ui.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.data.entity.MatchStatus
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.ui.theme.CsTvTheme

@Composable
internal fun MatchItem(
    match: MatchData,
    modifier: Modifier = Modifier,
    onClick: (MatchData) -> Unit = {},
) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier =
            modifier
                .height(dimensionResource(id = R.dimen.match_container_height))
                .clip(roundedCorner)
                .clickable(onClick = { onClick(match) })
                .background(colorScheme.primary)
                .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        val stickerText =
            when (match.matchStatus) {
                MatchStatus.RUNNING -> stringResource(id = R.string.live_match)
                MatchStatus.FINISHED -> stringResource(id = R.string.finished_match, match.beginAt)
                MatchStatus.NOT_STARTED -> stringResource(id = R.string.scheduled_match, match.beginAt)
            }

        CsTvText(
            modifier =
                Modifier
                    .align(Alignment.End)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = dimensionResource(R.dimen.padding_xlarge),
                            topEnd = dimensionResource(R.dimen.padding_large),
                        ),
                    )
                    .background(getStickerBackground(match.matchStatus, colorScheme.secondary))
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            text = stickerText,
            style = CsTvTextStyle.BASE_SUBTLE,
            styleOverride = getStickerTextStyle(match.matchStatus),
        )

        Row(
            modifier =
                Modifier
                    .weight(5f),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TeamView(
                teamImage = match.team1Image,
                teamName = match.team1Name,
                horizontalAlignment = Alignment.End,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f),
            )

            Spacer(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(dimensionResource(id = R.dimen.padding_medium)),
            )
            CsTvText(
                text = stringResource(id = R.string.versus),
                style = CsTvTextStyle.SMALL_SUBTLE,
                styleOverride = TextStyle(color = colorScheme.onTertiary),
            )

            Spacer(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(dimensionResource(id = R.dimen.padding_medium)),
            )

            TeamView(
                teamImage = match.team2Image,
                teamName = match.team2Name,
                horizontalAlignment = Alignment.Start,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(2f),
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))

        HorizontalDivider(thickness = 1.dp)

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CsTvAsyncImage(
                modifier = Modifier.size(dimensionResource(id = R.dimen.league_thumbnail_image_size)),
                imageUrl = match.leagueImageUrl,
            )
            CsTvText(
                text = "${match.leagueName} + ${match.serieName}",
                style = CsTvTextStyle.X_SMALL_SUBTLE,
            )
        }
    }
}

@Composable
fun getStickerTextStyle(matchStatus: MatchStatus) =
    when (matchStatus) {
        MatchStatus.RUNNING -> TextStyle(color = colorScheme.onError)
        MatchStatus.FINISHED, MatchStatus.NOT_STARTED -> TextStyle(color = colorScheme.onSecondary)
    }

@Composable
fun getStickerBackground(
    matchStatus: MatchStatus,
    startColor: Color,
): Color {
    val colorScheme = MaterialTheme.colorScheme

    return when (matchStatus) {
        MatchStatus.RUNNING -> {
            val color = remember { Animatable(startColor) }
            LaunchedEffect(Unit) {
                color.animateTo(
                    targetValue = colorScheme.errorContainer,
                    animationSpec =
                        infiniteRepeatable(
                            animation = tween(1000),
                            repeatMode = RepeatMode.Reverse,
                        ),
                )
            }
            color.value
        }

        MatchStatus.FINISHED, MatchStatus.NOT_STARTED -> startColor
    }
}

@Preview
@Composable
internal fun MatchItemPreviewLive() {
    CsTvTheme {
        MatchItem(stubMatch(1))
    }
}

@Preview
@Composable
internal fun MatchItemPreviewScheduled() {
    CsTvTheme {
        MatchItem(stubMatch(1).copy(matchStatus = MatchStatus.NOT_STARTED))
    }
}

@Preview
@Composable
internal fun MatchItemPreviewFinished() {
    CsTvTheme {
        MatchItem(stubMatch(1).copy(matchStatus = MatchStatus.FINISHED))
    }
}

internal fun stubMatch(id: Int) =
    MatchData(
        id = id,
        team1Name = "Really Really long team name $id",
        team2Name = "name 2 $id",
        leagueImageUrl = "",
        matchStatus = MatchStatus.RUNNING,
        beginAt = "beginAt",
        leagueName = "League name",
        serieName = "Serie name",
    )
