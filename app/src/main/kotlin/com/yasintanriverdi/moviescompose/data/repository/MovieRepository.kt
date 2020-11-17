package com.yasintanriverdi.moviescompose.data.repository

import com.yasintanriverdi.moviescompose.data.datasource.MovieRemoteDataSource
import com.yasintanriverdi.moviescompose.data.mapper.MoviesResponseToMovieListMapper
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import com.yasintanriverdi.moviescompose.model.toRepositoryResult
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieListMapper: MoviesResponseToMovieListMapper
) {

    suspend fun fetchMovies(page: Int, category: String): RepositoryResult<List<Movie>> {
        // TODO - check DB first
        val result = movieRemoteDataSource.fetchMovies(page, category).toRepositoryResult(movieListMapper) // TODO - use mapper

        if (result is RepositoryResult.Success) {
            // TODO - save movies to DB
        }

        return result
    }
}
