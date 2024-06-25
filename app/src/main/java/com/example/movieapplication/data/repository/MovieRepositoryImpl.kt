package com.example.movieapplication.data.repository

import com.example.movieapplication.data.remote.ApiService
import com.example.movieapplication.data.remote.response.toMovie
import com.example.movieapplication.domain.model.Movie
import com.example.movieapplication.domain.repository.MovieRepository


class MovieRepositoryImpl (private val apiService: ApiService) : MovieRepository {
    override suspend fun getNowPlayingMovies(): List<Movie> {
        return  apiService.getMovieNowPlaying().results.map { it.toMovie() }
    }


    override suspend fun getMovieDetail(query: String): Movie {
        return apiService.getMovieDetail(query).toMovie()
    }
}