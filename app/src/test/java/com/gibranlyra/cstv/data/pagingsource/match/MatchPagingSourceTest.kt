package com.gibranlyra.cstv.data.pagingsource.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.gibranlyra.cstv.CoroutineTestRule
import com.gibranlyra.cstv.data.ext.toMatchData
import com.gibranlyra.cstv.data.match.MatchDataSource
import com.gibranlyra.cstv.domain.model.MatchData
import com.gibranlyra.cstv.stub.MatchListStub
import com.gibranlyra.cstv.util.getCurrentDateTime
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class MatchPagingSourceTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val matchDataSource = mockk<MatchDataSource>()

    private val matchPagingSource: MatchPagingSource
        get() {
            return MatchPagingSource(matchDataSource)
        }

    @Before
    fun setup() {
        mockkStatic(::getCurrentDateTime)
    }

    @Test
    fun `when pagingSourceLoad should return error`() =
        runTest {
            val error = RuntimeException("Unexpected Error", Throwable())
            val pageSize = 20
            val pageNumber = 20
            val filter = "filter"

            coEvery { matchDataSource.getMatches(pageSize, pageNumber, filter) } throws error
            coEvery { getCurrentDateTime(0) } returns filter

            val expectedResult = PagingSource.LoadResult.Error<Int, MatchData>(error)

            assertEquals(
                expectedResult,
                matchPagingSource.load(PagingSource.LoadParams.Refresh(pageSize, pageNumber, false)),
            )
        }

    @Test
    fun `when pagingSource loaded successfully then should return results`() =
        runTest {
            val pageSize = 20
            val pageNumber = 0
            val filter = "filter"
            val response = MatchListStub()

            coEvery { matchDataSource.getMatches(pageNumber, pageNumber, filter) } returns response
            coEvery { matchDataSource.getMatches(pageSize, pageNumber, filter) } returns response
            coEvery { getCurrentDateTime(0) } returns filter

            val expectedResult =
                PagingSource.LoadResult.Page(response.map { it.toMatchData() }, null, 1)

            assertEquals(
                expectedResult,
                matchPagingSource.load(PagingSource.LoadParams.Refresh(0, 1, false)),
            )
        }

    @Test
    fun `when pagingSource append then should request nextPage values and returns success`() =
        runTest {
            val pageSize = 20
            val pageNumber = 1
            val filter = "filter"
            val response = MatchListStub()

            coEvery { matchDataSource.getMatches(pageSize, pageNumber, filter) } returns response
            coEvery { getCurrentDateTime(0) } returns filter

            val expectedResult =
                PagingSource.LoadResult.Page(response.map { it.toMatchData() }, null, 2)

            assertEquals(
                expectedResult,
                matchPagingSource.load(PagingSource.LoadParams.Append(1, 1, false)),
            )
        }
}
