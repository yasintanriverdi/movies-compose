package com.yasintanriverdi.moviescompose.ui.movies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yasintanriverdi.moviescompose.data.usecase.FetchMoviesUseCase
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.RepositoryResult

class MoviePagingSource(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>) = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse =
                fetchMoviesUseCase.fetchMovies(nextPage) as RepositoryResult.Success

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
