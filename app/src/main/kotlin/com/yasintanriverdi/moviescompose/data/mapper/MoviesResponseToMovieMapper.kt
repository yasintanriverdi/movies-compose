package com.yasintanriverdi.moviescompose.data.mapper

import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.network.model.entity.MovieResponseItem
import javax.inject.Inject

class MoviesResponseToMovieMapper @Inject constructor() :
    Mapper<List<MovieResponseItem>, List<Movie>> {

    override suspend fun map(from: List<MovieResponseItem>): List<Movie> {
        return from.map {
            Movie(
                id = it.id,
                posterUrl = it.posterUrl,
                backdropUrl = it.backdropUrl,
                overview = it.overview,
                releaseDate = it.releaseDate,
                hasVideo = it.hasVideo,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                imdbId = it.imdbId,
                homePage = it.homePage,
                title = it.title
            )
        }
    }
}
