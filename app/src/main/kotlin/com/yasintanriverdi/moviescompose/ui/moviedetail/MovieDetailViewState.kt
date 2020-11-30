package com.yasintanriverdi.moviescompose.ui.moviedetail

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.UIState

data class MovieDetailViewState(
    val uiState: UIState = UIState.IDLE,
    val movie: Movie? = null
)
