package com.example.movieapplication.domain.usecase

import com.example.movieapplication.domain.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {
    suspend fun getMovieNowPlaying() = repository.getMovieNowPlaying()
    suspend fun getMovieDetail(query: String) = repository.getMovieDetail(query)
}