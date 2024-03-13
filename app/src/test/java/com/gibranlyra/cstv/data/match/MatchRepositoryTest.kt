package com.gibranlyra.cstv.data.match

import androidx.paging.Pager
import androidx.paging.PagingData
import app.cash.turbine.test
import com.gibranlyra.cstv.data.ext.toMatchData
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.stub.MatchListStub
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class MatchRepositoryTest {
    private val pagerMock = mockk<Pager<Int, MatchData>>()

    private val matchRepository: MatchRepository
        get() {
            return MatchRepository(pagerMock)
        }

    @Test
    fun `when getMatches should make remote call and return PagingData`() = runTest {
        // Given
        val matches = MatchListStub()
        val pagingData = PagingData.from(matches.map { match -> match.toMatchData() })
        val pagingDataFlow = flowOf(pagingData)

        coEvery { pagerMock.flow } returns pagingDataFlow

        // When
        matchRepository.getMatches()

        // Then
        pagerMock.flow.test {
            assertEquals(pagingData, awaitItem())
            awaitComplete()
        }
    }
}
