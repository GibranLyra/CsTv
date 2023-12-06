package com.gibranlyra.fuzecctest.ui.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.data.entity.MatchStatus
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.PandaImage
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme

@Composable
internal fun MatchItem(
    match: MatchData,
    modifier: Modifier = Modifier,
    onClick: (MatchData) -> Unit = {},
) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier = modifier
            .height(212.dp)
            .clip(roundedCorner)
            .clickable(onClick = { onClick(match) })
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val stickerText = when (match.matchStatus) {
            MatchStatus.RUNNING -> stringResource(id = R.string.live_match)
            MatchStatus.FINISHED -> stringResource(id = R.string.finished_match, match.beginAt)
            MatchStatus.NOT_STARTED -> stringResource(id = R.string.scheduled_match, match.beginAt)
        }

        FuzeText(
            modifier = Modifier
                .align(Alignment.End)
                .clip(
                    RoundedCornerShape(
                        bottomStart = dimensionResource(R.dimen.padding_large),
                        topEnd = dimensionResource(R.dimen.padding_large)
                    )
                )
                .background(getStickerBackground(match.matchStatus))
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            text = stickerText,
            style = FuzeTextStyle.BASE_SUBTLE,
            styleOverride = getStickerTextStyle(match.matchStatus)
        )

        Row(
            modifier = Modifier
                .weight(5f),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TeamView(match.team1Image, match.team1Name)

            FuzeText(
                text = stringResource(id = R.string.versus),
                style = FuzeTextStyle.X_SMALL_SUBTLE
            )

            TeamView(match.team2Image, match.team2Name)
        }

        Divider(thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FuzeAsyncImage(
                modifier = Modifier.size(dimensionResource(id = R.dimen.league_thumbnail_image_size)),
                imageUrl = match.leagueImageUrl
            )
            FuzeText(
                text = "${match.leagueName} + ${match.serieName}",
                style = FuzeTextStyle.X_SMALL_SUBTLE
            )
        }
    }
}

@Composable
private fun TeamView(
    teamImage: PandaImage,
    teamName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.width(120.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        FuzeAsyncImage(
            imageUrl = teamImage.getImage(PandaImage.ImageType.THUMBNAIL),
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.large_match_team_image_width))
                .weight(2f),
        )

        FuzeText(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = teamName
        )
    }
}

@Composable
private fun getStickerTextStyle(matchStatus: MatchStatus) = when (matchStatus) {
    MatchStatus.RUNNING -> TextStyle(color = MaterialTheme.colorScheme.onPrimary)
    MatchStatus.FINISHED, MatchStatus.NOT_STARTED -> TextStyle(color = MaterialTheme.colorScheme.onTertiary)
}

@Composable
fun getStickerBackground(matchStatus: MatchStatus): Color {
    val colorScheme = MaterialTheme.colorScheme

    return when (matchStatus) {
        MatchStatus.RUNNING -> {
            val color = remember { Animatable(colorScheme.tertiary) }
            LaunchedEffect(Unit) {
                color.animateTo(
                    targetValue = Color.Red,
                    animationSpec = infiniteRepeatable(
                        animation = tween(1000),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }
            color.value
        }

        MatchStatus.FINISHED, MatchStatus.NOT_STARTED -> colorScheme.tertiary
    }
}

@Preview
@Composable
internal fun MatchItemPreviewLive() {
    FuzeccTheme {
        MatchItem(stubMatch(1))
    }
}

@Preview
@Composable
internal fun MatchItemPreviewScheduled() {
    FuzeccTheme {
        MatchItem(stubMatch(1).copy(matchStatus = MatchStatus.NOT_STARTED))
    }
}

@Preview
@Composable
internal fun MatchItemPreviewFinished() {
    FuzeccTheme {
        MatchItem(stubMatch(1).copy(matchStatus = MatchStatus.FINISHED))
    }
}

internal fun stubMatch(id: Int) = MatchData(
    id = id,
    team1Name = "name 1 $id",
    team2Name = "name 2 $id",
    leagueImageUrl = "",
    matchStatus = MatchStatus.RUNNING,
    beginAt = "beginAt",
    leagueName = "League name",
    serieName = "Serie name"
)
