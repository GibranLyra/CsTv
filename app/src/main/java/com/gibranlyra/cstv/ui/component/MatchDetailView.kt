package com.gibranlyra.cstv.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.data.entity.MatchStatus
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.ui.theme.CsTvTheme

@Composable
internal fun MatchDetailView(match: MatchData, modifier: Modifier = Modifier) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))
    val stickerText = getStickerText(match)

    Column(
        modifier =
        modifier
            .height(dimensionResource(id = R.dimen.match_details_container_height))
            .clip(roundedCorner)
            .background(colorScheme.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Row(
                modifier =
                Modifier
                    .weight(5f),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_medium),
                ),
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

            CsTvText(
                modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(roundedCorner)
                    .background(getStickerBackground(match.matchStatus, colorScheme.background))
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                text = stickerText,
                style = CsTvTextStyle.BASE_SUBTLE,
                styleOverride = getStickerTextStyle(match.matchStatus),
            )
        }
    }
}

@Composable
private fun getStickerText(match: MatchData) = when (match.matchStatus) {
    MatchStatus.RUNNING -> stringResource(id = R.string.live_match)
    MatchStatus.FINISHED -> stringResource(id = R.string.finished_match, match.beginAt)
    MatchStatus.NOT_STARTED ->
        stringResource(
            id = R.string.scheduled_match,
            match.beginAt,
        )
}

@Preview
@Composable
internal fun MatchDetailViewPreview() {
    CsTvTheme(darkTheme = true) {
        MatchDetailView(stubMatch(1))
    }
}
