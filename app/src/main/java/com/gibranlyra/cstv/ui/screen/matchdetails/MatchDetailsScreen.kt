package com.gibranlyra.cstv.ui.screen.matchdetails

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.domain.model.MatchDetailsTeamsData
import com.gibranlyra.cstv.domain.model.PandaImage
import com.gibranlyra.cstv.domain.model.PlayerData
import com.gibranlyra.cstv.domain.model.State
import com.gibranlyra.cstv.domain.model.TeamData
import com.gibranlyra.cstv.domain.model.ToolbarData
import com.gibranlyra.cstv.ui.component.CsTvAsyncImage
import com.gibranlyra.cstv.ui.component.CsTvCircularLoading
import com.gibranlyra.cstv.ui.component.CsTvText
import com.gibranlyra.cstv.ui.component.CsTvTextStyle
import com.gibranlyra.cstv.ui.component.MatchDetailView
import com.gibranlyra.cstv.ui.component.RetryButton
import com.gibranlyra.cstv.ui.component.stubMatch
import com.gibranlyra.cstv.ui.theme.CsTvTheme

@Composable
internal fun MatchDetailsScreen(
    uiState: MatchDetailsUiState,
    modifier: Modifier = Modifier,
    onToolbarComposition: (ToolbarData<String>) -> Unit = { },
    onRetryClick: (Int) -> Unit = { },
) {
    UpdateTopBar(uiState, onToolbarComposition)

    Column(
        modifier =
            modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_large))
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
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
    MatchDetailView(match = matchData)
}

@Composable
private fun MatchDetailsScreenLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CsTvCircularLoading(modifier = Modifier.align(alignment = Alignment.CenterHorizontally))
    }
}

@Composable
private fun MatchDetailsScreenTeamsLoaded(
    teamsData: MatchDetailsTeamsData,
    modifier: Modifier = Modifier,
) {
    PlayersPanelView(teamsData = teamsData, modifier = modifier)
}

@Composable
fun MatchScreenMatchError(modifier: Modifier = Modifier) {
    CsTvText(
        modifier = modifier,
        text = stringResource(id = R.string.match_detail_error_match_cannot_be_loaded),
    )
}

@Composable
fun MatchScreenTeamsError(
    modifier: Modifier = Modifier,
    onRetryClick: (Int) -> Unit,
) {
    RetryButton<Int>(
        message = stringResource(id = R.string.match_detail_error_team_cannot_be_loaded),
        modifier = modifier,
    ) { teamId ->
        teamId?.let { onRetryClick.invoke(teamId) }
    }
}

@Composable
private fun PlayersPanelView(
    teamsData: MatchDetailsTeamsData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            ) {
                teamsData.team1.players.forEach { player -> PlayerViewLeft(player = player) }
            }

            Column(
                Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            ) {
                teamsData.team2.players.forEach { player -> PlayerViewRight(player = player) }
            }
        }
    }
}

@Composable
private fun PlayerViewLeft(
    player: PlayerData,
    modifier: Modifier = Modifier,
) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier =
            modifier
                .height(dimensionResource(id = R.dimen.player_container_height))
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                .clip(roundedCorner)
                .background(MaterialTheme.colorScheme.primary),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
            ) {
                CsTvText(
                    text = player.nickName,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    style = CsTvTextStyle.SMALL,
                    maxLines = 2,
                )
                CsTvText(
                    text = player.name,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                    style = CsTvTextStyle.SMALL_SUBTLE,
                    styleOverride = TextStyle(color = MaterialTheme.colorScheme.onTertiary),
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))

            PlayerImage(roundedCorner, player)
        }
    }
}

@Composable
private fun PlayerViewRight(
    player: PlayerData,
    modifier: Modifier = Modifier,
) {
    val roundedCorner = RoundedCornerShape(dimensionResource(R.dimen.padding_large))

    Column(
        modifier =
            modifier
                .height(dimensionResource(id = R.dimen.player_container_height))
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.padding_medium))
                .clip(roundedCorner)
                .background(MaterialTheme.colorScheme.primary),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_medium)),
        ) {
            PlayerImage(roundedCorner = roundedCorner, player = player)

            Spacer(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))

            Column(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
            ) {
                CsTvText(
                    text = player.nickName,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = CsTvTextStyle.SMALL,
                )
                CsTvText(
                    text = player.name,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    style = CsTvTextStyle.SMALL_SUBTLE,
                    styleOverride = TextStyle(color = MaterialTheme.colorScheme.onTertiary),
                    maxLines = 2,
                )
            }
        }
    }
}

@Composable
private fun PlayerImage(
    roundedCorner: RoundedCornerShape,
    player: PlayerData,
) {
    CsTvAsyncImage(
        modifier =
            Modifier
                .clip(roundedCorner)
                .fillMaxHeight()
                .width(dimensionResource(id = R.dimen.player_image_width)),
        imageUrl = player.playerImage.getImage(PandaImage.ImageType.THUMBNAIL),
    )
}

@Composable
private fun UpdateTopBar(
    uiState: MatchDetailsUiState,
    onToolbarComposition: (ToolbarData<String>) -> Unit,
) {
    val defaultTopBarTitle = stringResource(id = R.string.match_detail_screen_default_title)

    when (val state = uiState.matchState) {
        is State.Loaded ->
            onToolbarComposition(
                ToolbarData(
                    title = "${state.data.leagueName} + ${state.data.serieName}",
                ),
            )

        else -> onToolbarComposition(ToolbarData(title = defaultTopBarTitle))
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchDetailsScreenLoadedPreview() {
    CsTvTheme(darkTheme = true) {
        MatchDetailsScreen(
            uiState =
                MatchDetailsUiState(
                    matchState = State.Loaded(stubMatch(111)),
                    teamsState = State.Loaded(stubMatchDetailsTeamsData()),
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchDetailsScreenLoadingPreview() {
    CsTvTheme {
        MatchDetailsScreen(uiState = MatchDetailsUiState())
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchDetailsScreenErrorPreview() {
    CsTvTheme {
        MatchDetailsScreen(
            uiState =
                MatchDetailsUiState(
                    matchState = State.Error(R.string.match_detail_error_match_cannot_be_loaded),
                    teamsState = State.Error(R.string.match_detail_error_team_cannot_be_loaded),
                ),
        )
    }
}

internal fun stubMatchDetailsTeamsData(): MatchDetailsTeamsData {
    return MatchDetailsTeamsData(team1 = stubTeamData(1), team2 = stubTeamData(2, 5))
}

internal fun stubTeamData(
    id: Int,
    size: Int = 10,
) = TeamData(teamId = id, name = "Team Name $id", stubPlayersDataList(size))

internal fun stubPlayersDataList(size: Int) = List(size) { index -> stubPlayerData(index) }

internal fun stubPlayerData(id: Int) = PlayerData(playerId = id, nickName = "NickName $id", name = "PlayerName PlayerLastName $id")
