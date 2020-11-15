package com.yasintanriverdi.moviescompose.network.model.entity

import com.squareup.moshi.Json

data class MovieResponseItem(
    val id: Int,
    @field:Json(name = "poster_path") val posterUrl: String?,
    @field:Json(name = "backdrop_path") val backdropUrl: String?,
    val overview: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    val title: String,
    @field:Json(name = "video") val hasVideo: Boolean,
    @field:Json(name = "vote_average") val voteAverage: Float,
    @field:Json(name = "vote_count") val voteCount: Int,
    val homePage: String?,
    @field:Json(name = "imdb_id") val imdbId: String?
)
