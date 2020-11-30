package com.yasintanriverdi.moviescompose.data.usecase

import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import javax.inject.Inject

class FetchMovieItemUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun fetchMovieById(movieId: String) = movieRepository.fetchMovieById(movieId)
}
