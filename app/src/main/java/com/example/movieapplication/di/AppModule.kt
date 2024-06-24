package com.example.movieapplication.di

import com.example.movieapplication.api.ApiClient
import com.example.movieapplication.data.remote.RemoteDataSource
import com.example.movieapplication.domain.db.UserDatabase
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.repository.UserRepository
import com.example.movieapplication.presentation.viewModel.AuthViewModel
import com.example.movieapplication.presentation.viewModel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val dataModule
        get() = module {
            single { ApiClient.create() }
            single { MyDataStore(get()) }
            single {
                UserDatabase.getInstance(androidContext()).userDao
            }
            factory { RemoteDataSource(get()) }
            factory { UserRepository(get(), get()) }
            factory { MovieRepository(get()) }


        }


    val uiModule
        get() = module {
            viewModel { AuthViewModel(get()) }
            viewModel { MovieViewModel(get()) }
        }

}
