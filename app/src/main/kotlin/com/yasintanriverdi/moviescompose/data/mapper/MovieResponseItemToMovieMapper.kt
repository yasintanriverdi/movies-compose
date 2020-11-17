package com.yasintanriverdi.moviescompose.data.mapper

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.movieResponseItemToMovie
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import javax.inject.Inject

class MovieResponseItemToMovieMapper @Inject constructor() : Mapper<MovieResponseItem, Movie> {

    override fun map(from: MovieResponseItem) = movieResponseItemToMovie(from)
}
