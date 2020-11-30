package com.yasintanriverdi.moviescompose.data.datasource

import com.yasintanriverdi.moviescompose.network.service.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun fetchMovies(page: Int, category: String) = movieService.fetchMovies(category, page)

    suspend fun fetchMovieById(movieId: String) = movieService.fetchMovie(movieId)
}
