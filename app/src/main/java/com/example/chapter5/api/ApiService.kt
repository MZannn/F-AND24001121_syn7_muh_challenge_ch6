package com.example.chapter5.api

import com.example.chapter5.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Header("Authorization") apikey: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOTcxMDY2Yjk2ZDMxZWE0YTg2NmUyYjk4NDMzYjg5YyIsInN1YiI6IjY2M2Q4ZTlhZTBkNDdhNjc3YzMwNzQ3MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._oX4IRDwZjQPoEa9Bebi3xFdEGP7DWasfV8uMN6soFg"
    ): Call<MovieResponse>

}