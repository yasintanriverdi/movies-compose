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
    val title: String
)

fun Movie.toMovieResponseItem(): MovieResponseItem {
    return MovieResponseItem(
        id = this.id,
        posterUrl = this.posterUrl,
        backdropUrl = this.backdropUrl,
        overview = this.overview,
        releaseDate = this.releaseDate,
        hasVideo = this.hasVideo,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        imdbId = this.imdbId,
        homePage = this.homePage,
        title = this.title
    )
}
