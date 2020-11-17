package com.yasintanriverdi.moviescompose.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun fetchMovies() {
        viewModelScope.launch {
            when (val movieResult = movieRepository.fetchMovies(page = 1, category = "popular")) {
                is RepositoryResult.Success -> {
                }
                else -> {
                }
            }
        }
    }
}
