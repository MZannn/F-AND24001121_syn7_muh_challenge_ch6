package com.example.movieapplication.data.remote

import com.example.movieapplication.data.remote.ApiService


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovieNowPlaying() = apiService.getMovieNowPlaying()
    suspend fun getMovieDetail(query: String) = apiService.getMovieDetail(query)
}
