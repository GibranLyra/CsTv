package com.gibranlyra.cstv.ui.screen.matchdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.gibranlyra.cstv.CoroutineTestRule
import com.gibranlyra.cstv.R
import com.gibranlyra.cstv.data.entity.Result
import com.gibranlyra.cstv.data.ext.toMatchData
import com.gibranlyra.cstv.data.ext.toTeamData
import com.gibranlyra.cstv.domain.matchdetails.MatchDetailsScreenShowMatchUseCase
import com.gibranlyra.cstv.domain.matchdetails.MatchDetailsScreenTeamUseCase
import com.gibranlyra.cstv.domain.model.MatchDetailsTeamsData
import com.gibranlyra.cstv.domain.model.State
import com.gibranlyra.cstv.stub.MatchListStub
import com.gibranlyra.cstv.stub.TeamStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class MatchDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val matchDetailsShowMatchUseCase = mockk<MatchDetailsScreenShowMatchUseCase>()
    private val matchDetailsTeamUseCase = mockk<MatchDetailsScreenTeamUseCase>()

    private val viewModel: MatchDetailsViewModel by lazy {
        MatchDetailsViewModel(
            dispatcherProvider = coroutineTestRule.testDispatcherProvider,
            matchId = 0,
            matchDetailsScreenShowMatchUseCase = matchDetailsShowMatchUseCase,
            matchDetailsScreenTeamUseCase = matchDetailsTeamUseCase
        )
    }

    @Test
    fun `when start should call getTeams and receive matchDetails and teamDetails successFully`() =
        runTest {
            val team1 = TeamStub(0).toTeamData()
            val team2 = TeamStub(1).toTeamData()
            val match = MatchListStub().first().toMatchData()
            val matchDetailsTeamsData = MatchDetailsTeamsData(team1, team2)
            val expectedMatchResult = Result.Success(match)
            val expectedTeamsResult = Result.Success(matchDetailsTeamsData)

            val expectedMatchDetailsState = MatchDetailsUiState(
                matchState = State.Loaded(match),
                teamsState = State.Loaded(matchDetailsTeamsData)
            )

            coEvery { matchDetailsShowMatchUseCase.invoke(match.id) } returns flowOf(
                expectedMatchResult
            )
            coEvery {
                matchDetailsTeamUseCase.invoke(team1.teamId, team2.teamId)
            } returns flowOf(expectedTeamsResult)

            viewModel.uiState.test {
                Assert.assertEquals(MatchDetailsUiState(), awaitItem())
                Assert.assertEquals(
                    expectedMatchDetailsState.copy(teamsState = State.Uninitialized()),
                    awaitItem()
                )
                Assert.assertEquals(expectedMatchDetailsState, awaitItem())
            }
        }

    @Test
    fun `when start should call getTeams and receive matchDetails success and teamDetails error`() =
        runTest {
            val team1 = TeamStub(0).toTeamData()
            val team2 = TeamStub(1).toTeamData()
            val match = MatchListStub().first().toMatchData()
            val expectedMatchResult = Result.Success(match)
            val expectedTeamsResult = Result.Error("")

            val expectedMatchDetailsState = MatchDetailsUiState(
                matchState = State.Loaded(match),
                teamsState = State.Error(R.string.match_detail_error_team_cannot_be_loaded)
            )

            coEvery { matchDetailsShowMatchUseCase.invoke(match.id) } returns flowOf(
                expectedMatchResult
            )
            coEvery { matchDetailsTeamUseCase.invoke(team1.teamId, team2.teamId) } returns flowOf(
                expectedTeamsResult
            )

            viewModel.uiState.test {
                Assert.assertEquals(MatchDetailsUiState(), awaitItem())
                Assert.assertEquals(
                    expectedMatchDetailsState.copy(teamsState = State.Uninitialized()),
                    awaitItem()
                )
                Assert.assertEquals(expectedMatchDetailsState, awaitItem())
            }
        }

    @Test
    fun `when start should call getTeams and receive matchDetails error and teamDetails error`() =
        runTest {
            val team1 = TeamStub(0).toTeamData()
            val team2 = TeamStub(1).toTeamData()
            val match = MatchListStub().first().toMatchData()
            val expectedMatchResult = Result.Error("")
            val expectedTeamsResult = Result.Error("")

            val expectedMatchDetailsState = MatchDetailsUiState(
                matchState = State.Error(R.string.match_detail_error_match_cannot_be_loaded),
                teamsState = State.Error(R.string.match_detail_error_team_cannot_be_loaded)
            )

            coEvery { matchDetailsShowMatchUseCase.invoke(match.id) } returns flowOf(
                expectedMatchResult
            )
            coEvery { matchDetailsTeamUseCase.invoke(team1.teamId, team2.teamId) } returns flowOf(
                expectedTeamsResult
            )

            viewModel.uiState.test {
                Assert.assertEquals(MatchDetailsUiState(), awaitItem())
                Assert.assertEquals(
                    expectedMatchDetailsState.copy(teamsState = State.Uninitialized()),
                    awaitItem()
                )
                Assert.assertEquals(expectedMatchDetailsState, awaitItem())
            }
        }
}