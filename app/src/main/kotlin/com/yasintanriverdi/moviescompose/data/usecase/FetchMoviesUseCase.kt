package com.yasintanriverdi.moviescompose.data.usecase

import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun fetchMovies(page: Int) = movieRepository.fetchMovies(page)
}