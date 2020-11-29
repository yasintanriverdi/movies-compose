package com.yasintanriverdi.moviescompose.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.ui.movies.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MoviesViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(movieRepository)
    }.flow

}