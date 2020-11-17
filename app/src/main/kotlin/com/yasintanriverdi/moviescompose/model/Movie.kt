package com.yasintanriverdi.moviescompose.model

import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem

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

fun movieResponseItemToMovie(from: MovieResponseItem): Movie {
    return Movie(
        id = from.id,
        posterUrl = from.posterUrl,
        backdropUrl = from.backdropUrl,
        overview = from.overview,
        releaseDate = from.releaseDate,
        hasVideo = from.hasVideo,
        voteAverage = from.voteAverage,
        voteCount = from.voteCount,
        imdbId = from.imdbId,
        homePage = from.homePage,
        title = from.title
    )
}
