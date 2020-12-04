package com.yasintanriverdi.moviescompose.network.model.response

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import com.yasintanriverdi.moviescompose.network.model.entity.toMovie

data class MovieListResponse(
    val results: List<MovieResponseItem>
)

fun MovieListResponse.toMovieList(): List<Movie> {
    return this.results.map(MovieResponseItem::toMovie)
}
