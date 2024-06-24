package com.example.movieapplication.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.data.remote.response.MovieDetailResponse
import com.example.movieapplication.data.remote.response.MovieResponse
import com.example.movieapplication.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private var _movieDetailResponse = MutableLiveData<MovieDetailResponse?>()
    val movieDetailResponse: MutableLiveData<MovieDetailResponse?> get() = _movieDetailResponse
    private val _movieResponse = MutableLiveData<MovieResponse?>()
    val movieResponse: LiveData<MovieResponse?> = _movieResponse

    fun getMovieNowPlaying() {
        viewModelScope.launch {
            try {
                val response = movieUseCase.getMovieNowPlaying()
                _movieResponse.postValue(response)
            } catch (e: Exception) {
                _movieResponse.postValue(null)
                Log.e("MovieViewModel", "Failed to fetch movie data", e)

            }
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            try {
                val response = movieUseCase.getMovieDetail(id)
                _movieDetailResponse.postValue(response)
            } catch (e: Exception) {
                _movieDetailResponse.postValue(null)
            }
        }
    }

}