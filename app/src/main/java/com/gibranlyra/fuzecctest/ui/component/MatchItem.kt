package com.gibranlyra.fuzecctest.ui.component

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gibranlyra.fuzecctest.R
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
            .background(MaterialTheme.colorScheme.onPrimary)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FuzeText(
            modifier = Modifier
                .align(Alignment.End)
                .clip(
                    RoundedCornerShape(
                        bottomStart = dimensionResource(R.dimen.padding_large),
                        topEnd = dimensionResource(R.dimen.padding_large)
                    )
                )
                .background(MaterialTheme.colorScheme.secondary)
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            text = match.beginAt,
            style = FuzeTextStyle.BASE_SUBTLE,
            styleOverride = TextStyle(color = MaterialTheme.colorScheme.onSecondary)
        )

        Row(
            modifier = Modifier
                .weight(5f),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TeamView(match.team1Image, match.team1Name)

            FuzeText(text = stringResource(id = R.string.versus))

            TeamView(match.team2Image, match.team2Name)
        }

        Divider(thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FuzeAsyncImage(imageUrl = match.leagueImageUrl)
            FuzeText(text = "${match.leagueName} + ${match.serieName}")
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
                .size(64.dp)
                .weight(2f),
        )

        FuzeText(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = teamName
        )
    }
}

@Preview
@Composable
internal fun MatchItemPreview() {
    FuzeccTheme {
        MatchItem(stubMatch(1))
    }
}

internal fun stubMatch(id: Int) = MatchData(
    id = id,
    team1Name = "name 1 $id",
    team2Name = "name 2 $id",
    leagueImageUrl = "",
    isLive = true,
    beginAt = "beginAt",
    leagueName = "League name",
    serieName = "Serie name"
)
