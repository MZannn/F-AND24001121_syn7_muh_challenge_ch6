package com.example.movieapplication.repository

import com.example.movieapplication.api.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource ) {

        suspend fun getMovieNowPlaying() = remoteDataSource.getMovieNowPlaying()
        suspend fun getMovieDetail(query: String) = remoteDataSource.getMovieDetail(query)
}