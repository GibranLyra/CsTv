package com.gibranlyra.fuzecctest.data.match

import com.gibranlyra.fuzecctest.data.entity.Match
import com.gibranlyra.fuzecctest.data.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val matchDataSource: MatchDataSource
) {

    fun getMatches(): Flow<Result<List<Match>>> =
        flow {
            try {
                val matches = matchDataSource.getMatches()
                emit(Result.Success(matches))
            } catch (e: HttpException) {
                emit(
                    Result.Error(e.localizedMessage ?: "Unexpected error")
                )
            } catch (e: IOException) {
                emit(Result.Error("No internet connection"))
            }
        }
}
