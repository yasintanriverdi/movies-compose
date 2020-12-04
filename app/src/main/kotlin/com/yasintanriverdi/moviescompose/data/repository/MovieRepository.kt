package com.yasintanriverdi.moviescompose.data.repository

import com.yasintanriverdi.moviescompose.data.datasource.MovieRemoteDataSource
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import com.yasintanriverdi.moviescompose.model.toRepositoryResult
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import com.yasintanriverdi.moviescompose.network.model.entity.toMovie
import com.yasintanriverdi.moviescompose.network.model.response.MovieListResponse
import com.yasintanriverdi.moviescompose.network.model.response.toMovieList
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) {

    suspend fun fetchMovies(
        page: Int,
        category: String = CATEGORY_POPULAR
    ): RepositoryResult<List<Movie>> =
        movieRemoteDataSource.fetchMovies(page, category)
            .toRepositoryResult(MovieListResponse::toMovieList)

    suspend fun fetchMovieById(movieId: String): RepositoryResult<Movie> =
        movieRemoteDataSource.fetchMovieById(movieId)
            .toRepositoryResult(MovieResponseItem::toMovie)

    companion object {
        const val CATEGORY_POPULAR = "popular"
    }
}
