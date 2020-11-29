package com.yasintanriverdi.moviescompose.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.yasintanriverdi.moviescompose.data.repository.MovieRepository
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult
import kotlinx.coroutines.flow.Flow

class MoviesViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(movieRepository)
    }.flow

}

class MovieSource(
    private val movieRepository: MovieRepository
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse =
                movieRepository.fetchMovies(nextPage) as RepositoryResult.Success

            LoadResult.Page(
                data = movieListResponse.result ?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}