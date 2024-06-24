package com.example.movieapplication.data.remote.response

import com.example.movieapplication.domain.model.Movie

data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
