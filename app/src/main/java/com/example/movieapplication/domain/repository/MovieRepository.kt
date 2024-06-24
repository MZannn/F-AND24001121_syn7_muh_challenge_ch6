package com.example.movieapplication.domain.repository

import com.example.movieapplication.data.remote.response.MovieDetailResponse
import com.example.movieapplication.data.remote.response.MovieResponse

interface MovieRepository {

    suspend fun getMovieNowPlaying(): MovieResponse
    suspend fun getMovieDetail(query: String): MovieDetailResponse
}