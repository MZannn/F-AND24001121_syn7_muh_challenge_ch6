package com.example.movieapplication.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.api.RemoteDataSource
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.repository.MovieRepository

class MovieViewModelFactory(private val remoteDataSource: RemoteDataSource, private val pref: MyDataStore) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        fun getInstance(context: Context): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(
                    RemoteDataSource(ApiClient.instance),
                    MyDataStore(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(remoteDataSource), pref) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

