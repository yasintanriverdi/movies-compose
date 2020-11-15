package com.yasintanriverdi.moviescompose.network.model.response

import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem

data class MovieListResponse(
    val results: List<MovieResponseItem>
)
