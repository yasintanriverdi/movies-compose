package com.yasintanriverdi.moviescompose.model

data class Movie(
    val id: Int,
    val posterUrl: String?,
    val backdropUrl: String?,
    val overview: String?,
    val releaseDate: String?,
    val hasVideo: Boolean,
    val voteAverage: Float,
    val voteCount: Int,
    val imdbId: String?,
    val homePage: String?,
    val title: String?
)
