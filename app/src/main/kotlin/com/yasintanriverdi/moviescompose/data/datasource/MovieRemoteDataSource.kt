package com.yasintanriverdi.moviescompose.data.datasource

import com.yasintanriverdi.moviescompose.model.Result
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import com.yasintanriverdi.moviescompose.network.model.response.MovieListResponse
import com.yasintanriverdi.moviescompose.network.service.MovieService
import javax.inject.Inject
import retrofit2.Response

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun fetchMovies(page: Int, category: String): Result<List<MovieResponseItem>> {
        return try {
            val response = movieService.fetchMovies(category, page)
            getMovieListResult(
                response = response,
                onError = {
                    Result.Error("Error fetching movies ${response.code()} ${response.message()}")
                }
            )
        } catch (e: Exception) {
            Result.Error("Error fetching movies")
        }
    }

    private inline fun getMovieListResult(
        response: Response<MovieListResponse>,
        onError: () -> Result.Error
    ): Result<List<MovieResponseItem>> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body.results)
            }
        }
        return onError.invoke()
    }
}
