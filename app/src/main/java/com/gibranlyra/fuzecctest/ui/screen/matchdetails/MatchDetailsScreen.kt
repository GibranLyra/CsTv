package com.gibranlyra.fuzecctest.ui.screen.matchdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gibranlyra.fuzecctest.R
import com.gibranlyra.fuzecctest.domain.model.MatchData
import com.gibranlyra.fuzecctest.domain.model.MatchDetailsTeamsData
import com.gibranlyra.fuzecctest.domain.model.PlayerData
import com.gibranlyra.fuzecctest.domain.model.State
import com.gibranlyra.fuzecctest.domain.model.TeamData
import com.gibranlyra.fuzecctest.domain.model.ToolbarData
import com.gibranlyra.fuzecctest.ui.component.FuzeAsyncImage
import com.gibranlyra.fuzecctest.ui.component.FuzeCircularLoading
import com.gibranlyra.fuzecctest.ui.component.FuzeText
import com.gibranlyra.fuzecctest.ui.component.FuzeTextStyle
import com.gibranlyra.fuzecctest.ui.component.MatchItem
import com.gibranlyra.fuzecctest.ui.component.RetryButton
import com.gibranlyra.fuzecctest.ui.component.stubMatch
import com.gibranlyra.fuzecctest.ui.theme.FuzeccTheme

@Composable
internal fun MatchDetailsScreen(
    uiState: MatchDetailsUiState,
    modifier: Modifier = Modifier,
    onToolbarComposition: (ToolbarData<String>) -> Unit = { },
    onRetryClick: (Int) -> Unit = { },
) {
    UpdateTopBar(uiState, onToolbarComposition)

    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_large))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val matchState = uiState.matchState) {
            is State.Loading, is State.Uninitialized -> MatchDetailsScreenLoading()
            is State.Loaded -> MatchDetailsScreenLoaded(matchData = matchState.data)
            is State.Error -> MatchScreenMatchError()
        }

        when (val teamState = uiState.teamsState) {
            is State.Loading, is State.Uninitialized -> MatchDetailsScreenLoading()
            is State.Loaded -> MatchDetailsScreenTeamsLoaded(teamsData = teamState.data)
            is State.Error -> MatchScreenTeamsError(onRetryClick = onRetryClick)
        }
    }
}

@Composable
private fun MatchDetailsScreenLoaded(matchData: MatchData) {
    MatchItem(match = matchData)
}

@Composable
private fun MatchDetailsScreenLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FuzeCircularLoading(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
    }
}


@Composable
private fun MatchDetailsScreenTeamsLoaded(
    teamsData: MatchDetailsTeamsData,
    modifier: Modifier = Modifier
) {
    PlayersPanelView(modifier, teamsData)
}

@Composable
fun MatchScreenMatchError(modifier: Modifier = Modifier) {
    FuzeText(
        modifier = modifier,
        text = stringResource(id = R.string.match_detail_error_match_cannot_be_loaded)
    )
}

@Composable
fun MatchScreenTeamsError(modifier: Modifier = Modifier, onRetryClick: (Int) -> Unit) {
    RetryButton<Int>(
        modifier = modifier,
        message = stringResource(id = R.string.match_detail_error_team_cannot_be_loaded),
        onClick = { teamId -> teamId?.let { onRetryClick(teamId) } }
    )
}

@Composable
private fun PlayersPanelView(
    modifier: Modifier,
    teamsData: MatchDetailsTeamsData
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                teamsData.team1.players.forEach { player -> PlayerViewLeft(player = player) }
            }

            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                teamsData.team2.players.forEach { player -> PlayerViewRight(player = player) }
            }
        }
    }
}

@Composable
private fun PlayerViewLeft(player: PlayerData, modifier: Modifier = Modifier) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier = modifier
            .height(106.dp)
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
            .clip(roundedCorner)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.padding_large))
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                FuzeText(
                    text = player.nickName,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    style = FuzeTextStyle.SMALL
                )
                FuzeText(
                    text = player.name,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    style = FuzeTextStyle.X_SMALL_SUBTLE
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))

            FuzeAsyncImage(
                modifier = Modifier
                    .clip(roundedCorner)
                    .fillMaxHeight()
                    .width(66.dp),
                imageUrl = player.imageUrl,
            )
        }
    }
}

@Composable
private fun PlayerViewRight(player: PlayerData, modifier: Modifier = Modifier) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier = modifier
            .height(106.dp)
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
            .clip(roundedCorner)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.padding_large))
        ) {
            FuzeAsyncImage(
                modifier = Modifier
                    .clip(roundedCorner)
                    .fillMaxHeight()
                    .width(66.dp),
                imageUrl = player.imageUrl,
            )

            Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                FuzeText(
                    text = player.nickName,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = FuzeTextStyle.SMALL
                )
                FuzeText(
                    text = player.name,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = FuzeTextStyle.X_SMALL_SUBTLE
                )
            }
        }
    }
}

@Composable
private fun UpdateTopBar(
    uiState: MatchDetailsUiState,
    onToolbarComposition: (ToolbarData<String>) -> Unit,
) {
    val defaultTopBarTitle = stringResource(id = R.string.match_detail_screen_default_title)

    when (val state = uiState.matchState) {
        is State.Loaded -> onToolbarComposition(
            ToolbarData(
                title = "${state.data.leagueName} + ${state.data.serieName}"
            )
        )

        else -> onToolbarComposition(ToolbarData(title = defaultTopBarTitle))
    }
}


@Preview(showBackground = true)
@Composable
private fun MatchDetailsScreenLoadedPreview() {
    FuzeccTheme {
        MatchDetailsScreen(
            uiState = MatchDetailsUiState(
                matchState = State.Loaded(stubMatch(111)),
                teamsState = State.Loaded(stubMatchDetailsTeamsData())
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchDetailsScreenLoadingPreview() {
    FuzeccTheme {
        MatchDetailsScreen(uiState = MatchDetailsUiState())
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchDetabilsScreenErrorPreview() {
    FuzeccTheme {
        MatchDetailsScreen(
            uiState = MatchDetailsUiState(
                matchState = State.Error(R.string.match_detail_error_match_cannot_be_loaded),
                teamsState = State.Error(R.string.match_detail_error_team_cannot_be_loaded)
            )
        )
    }
}


internal fun stubMatchDetailsTeamsData(): MatchDetailsTeamsData {
    return MatchDetailsTeamsData(team1 = stubTeamData(1), team2 = stubTeamData(2))
}

internal fun stubTeamData(id: Int) =
    TeamData(teamId = id, name = "Team Name $id", stubPlayersDataList(10))

internal fun stubPlayersDataList(size: Int) = List(size) { index -> stubPlayerData(index) }
internal fun stubPlayerData(id: Int) =
    PlayerData(playerId = id, nickName = "Player NickName $id", name = "Player $id")
