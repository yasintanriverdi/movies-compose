package com.yasintanriverdi.moviescompose.network.service

import com.yasintanriverdi.moviescompose.network.model.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{category}")
    suspend fun fetchMovies(
        @Path("category") category: String,
        @Query("page") page: Int
    ): Response<MovieListResponse>
}
