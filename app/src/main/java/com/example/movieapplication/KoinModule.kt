package com.example.movieapplication

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.api.RemoteDataSource
import com.example.movieapplication.db.UserDatabase
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.repository.MovieRepository
import com.example.movieapplication.repository.UserRepository
import com.example.movieapplication.viewModel.AuthViewModel
import com.example.movieapplication.viewModel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {

    val Application.dataModule
        get() = module {
            single { ApiClient.instance }
            single { MyDataStore(get()) }
            single {
                UserDatabase.getInstance(androidContext()).userDao
            }
            factory { RemoteDataSource(get()) }
            factory { UserRepository(get(), get()) }
            factory { MovieRepository(get()) }


        }

    val Application.uiModule
        get() = module {
            viewModel { AuthViewModel(get()) }
            viewModel { MovieViewModel(get(), get()) }
        }

}
