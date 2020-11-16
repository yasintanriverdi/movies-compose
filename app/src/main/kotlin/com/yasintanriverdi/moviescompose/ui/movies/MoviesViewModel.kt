package com.yasintanriverdi.moviescompose.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import com.yasintanriverdi.moviescompose.model.Result
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    // TODO - will be implemented
    fun fetchMovies() {
        viewModelScope.launch {
            when (val movieResult = movieRepository.fetchMovies(page = 1, category = "popular")) {
                is Result.Success -> {
                }
                is Result.Error -> {
                }
            }
        }
    }
}
