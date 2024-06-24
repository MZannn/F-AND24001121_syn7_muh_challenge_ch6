package com.example.movieapplication.domain.repository

import com.example.movieapplication.data.remote.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) {

        suspend fun getMovieNowPlaying() = remoteDataSource.getMovieNowPlaying()
        suspend fun getMovieDetail(query: String) = remoteDataSource.getMovieDetail(query)
}