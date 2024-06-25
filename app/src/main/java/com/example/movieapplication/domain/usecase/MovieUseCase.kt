package com.example.movieapplication.domain.usecase

import android.util.Log
import com.example.movieapplication.common.Resource
import com.example.movieapplication.data.remote.response.MovieResponse
import com.example.movieapplication.domain.model.Movie
import com.example.movieapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieUseCase(private val repository: MovieRepository) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading<List<Movie>>())
            val movies = repository.getNowPlayingMovies()
            emit(Resource.Success<List<Movie>>(movies))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>("Couldn't reach server. Check your internet connection."))
        }
    }
    operator fun invoke(id: String): Flow<Resource<Movie>> = flow {
        try {
            emit(Resource.Loading<Movie>())
            val movie = repository.getMovieDetail(id)
            emit(Resource.Success<Movie>(movie))
        } catch (e: HttpException) {
            emit(Resource.Error<Movie>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<Movie>("Couldn't reach server. Check your internet connection."))
        }
    }
}