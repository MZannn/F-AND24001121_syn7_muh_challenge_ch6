package com.example.movieapplication.api


class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovieNowPlaying() = apiService.getMovieNowPlaying()
    suspend fun getMovieDetail(query: String) = apiService.getMovieDetail(query)
}
