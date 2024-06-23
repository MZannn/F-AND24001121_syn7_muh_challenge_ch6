package com.example.movieapplication.usecase

import com.example.movieapplication.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {
    suspend fun getMovieNowPlaying() = repository.getMovieNowPlaying()
    suspend fun getMovieDetail(query: String) = repository.getMovieDetail(query)
}