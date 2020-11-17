package com.yasintanriverdi.moviescompose.data.mapper

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.movieResponseItemToMovie
import com.yasintanriverdi.moviescompose.network.model.response.MovieListResponse
import javax.inject.Inject

class MoviesResponseToMovieListMapper @Inject constructor() : Mapper<MovieListResponse, List<Movie>> {

    override fun map(from: MovieListResponse): List<Movie> {
        return from.results.map {
            movieResponseItemToMovie(it)
        }
    }
}
