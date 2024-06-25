package com.example.movieapplication.domain.repository

import com.example.movieapplication.data.remote.response.MovieDetailResponse
import com.example.movieapplication.data.remote.response.MovieResponse
import com.example.movieapplication.domain.model.Movie

interface MovieRepository {
    suspend fun getNowPlayingMovies(): List<Movie>

    suspend fun getMovieDetail(query: String): Movie
}