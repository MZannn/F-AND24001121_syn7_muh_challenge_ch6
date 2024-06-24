package com.example.movieapplication.data.repository

import com.example.movieapplication.data.remote.ApiService
import com.example.movieapplication.data.remote.response.MovieResponse
import com.example.movieapplication.domain.repository.MovieRepository

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {
    override suspend fun getMovieNowPlaying(): MovieResponse{
        return apiService.getMovieNowPlaying()
    }


    override suspend fun getMovieDetail(query: String) = apiService.getMovieDetail(query)
}