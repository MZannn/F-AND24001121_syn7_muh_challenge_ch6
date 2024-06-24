package com.example.movieapplication.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.domain.model.response.MovieDetailResponse
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.domain.model.response.MovieResponse
import com.example.movieapplication.domain.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private var _movieResponse = MutableLiveData<MovieResponse?>()
    private var _movieDetailResponse = MutableLiveData<MovieDetailResponse?>()
    val movieResponse: MutableLiveData<MovieResponse?> get() = _movieResponse
    val movieDetailResponse: MutableLiveData<MovieDetailResponse?> get() = _movieDetailResponse
    fun getMovieNowPlaying() {
        viewModelScope.launch {
            try {
                val response = repository.getMovieNowPlaying()
                _movieResponse.postValue(response)
            } catch (e: Exception) {
                _movieResponse.postValue(null)
            }
        }
    }
    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetail(id)
                _movieDetailResponse.postValue(response)
            } catch (e: Exception) {
                _movieDetailResponse.postValue(null)
            }
        }
    }

}