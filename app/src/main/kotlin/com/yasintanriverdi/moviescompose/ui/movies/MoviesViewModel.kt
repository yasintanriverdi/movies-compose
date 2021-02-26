package com.yasintanriverdi.moviescompose.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yasintanriverdi.moviescompose.data.usecase.FetchMovieItemUseCase
import com.yasintanriverdi.moviescompose.data.usecase.FetchMoviesUseCase
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import com.yasintanriverdi.moviescompose.model.UIState
import com.yasintanriverdi.moviescompose.ui.moviedetail.MovieDetailViewState
import com.yasintanriverdi.moviescompose.ui.movies.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val fetchMovieItemUseCase: FetchMovieItemUseCase
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(fetchMoviesUseCase)
    }.flow

    private val _viewStateFlow = MutableStateFlow(MovieDetailViewState())
    val viewStateFlow: StateFlow<MovieDetailViewState> = _viewStateFlow

    fun fetchMovie(movieId: String) {
        viewModelScope.launch {
            _viewStateFlow.value = viewStateFlow.value.copy(uiState = UIState.LOADING)
            when (val movieResult = fetchMovieItemUseCase.fetchMovieById(movieId)) {
                is RepositoryResult.Success -> {
                    _viewStateFlow.value = MovieDetailViewState(
                        uiState = if (movieResult.result != null) UIState.CONTENT else UIState.ERROR,
                        movie = movieResult.result
                    )
                }
                else -> {
                    _viewStateFlow.value = MovieDetailViewState(uiState = UIState.ERROR)
                }
            }
        }
    }
}
