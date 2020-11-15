package com.yasintanriverdi.moviescompose.data.repository

import com.yasintanriverdi.moviescompose.data.datasource.MovieRemoteDataSource
import com.yasintanriverdi.moviescompose.data.mapper.MoviesResponseToMovieMapper
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.Result

class MovieRepository constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieMapper: MoviesResponseToMovieMapper
) {

    suspend fun fetchMovies(page: Int, category: String): Result<List<Movie>> {
        return when (val response = movieRemoteDataSource.fetchMovies(page, category)) {
            is Result.Success -> {
                val movies = movieMapper.map(response.data)
                Result.Success(movies)
            }
            is Result.Error -> response
        }
    }
}
