package com.yasintanriverdi.moviescompose.data.mapper

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import javax.inject.Inject

class MovieResponseItemToMovieMapper @Inject constructor() :
    Mapper<MovieResponseItem, Movie> {

    override suspend fun map(from: MovieResponseItem): Movie {
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
}
