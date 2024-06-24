package com.example.movieapplication.data.remote

import com.example.movieapplication.domain.model.response.MovieDetailResponse
import com.example.movieapplication.domain.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(): MovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String
    ): MovieDetailResponse
}