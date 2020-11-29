package com.yasintanriverdi.moviescompose.ui.moviedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.yasintanriverdi.moviescompose.data.repository.MovieRepository

class MovieDetailViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

}