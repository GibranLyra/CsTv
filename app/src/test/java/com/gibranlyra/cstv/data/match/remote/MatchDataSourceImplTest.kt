package com.gibranlyra.cstv.data.match.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gibranlyra.cstv.CoroutineTestRule
import com.gibranlyra.cstv.data.match.MatchDataSource
import com.gibranlyra.cstv.stub.MatchListStub
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
internal class MatchDataSourceImplTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockedMatchApi: MatchApi = mockk()
    private val matchDataSourceImpl: MatchDataSource = MatchDataSourceImpl(mockedMatchApi)

    @Test
    fun `should return list of matches when getMatches is called`() =
        runTest {
            // Given
            val expectedMatches = MatchListStub()
            val pageSize = 0
            val pageNumber = 0
            val filter = ""

            coEvery { mockedMatchApi.getMatches(pageSize, pageNumber, filter) } returns expectedMatches
            // When
            val actualMatches = matchDataSourceImpl.getMatches(pageSize, pageNumber, filter)

            // Then
            assertEquals(expectedMatches, actualMatches)
        }

    @Test
    fun `should throw error of matches when getMatches is called and error occurs`() =
        runTest {
            // Given
            val expectedError = IOException()
            val pageSize = 0
            val pageNumber = 0
            val filter = ""

            coEvery { mockedMatchApi.getMatches(pageSize, pageNumber, filter) } throws expectedError

            try {
                // When
                matchDataSourceImpl.getMatches(pageSize, pageNumber, filter)
                fail("Expected exception not thrown")
            } catch (actualError: IOException) {
                // Then
                assertEquals(expectedError, actualError)
            } catch (e: Exception) {
                fail("Expected exception not thrown. Thrown exception:${e::class.simpleName}")
            }
        }
}
