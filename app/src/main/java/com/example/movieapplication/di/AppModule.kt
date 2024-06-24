package com.example.movieapplication.di

import com.example.movieapplication.data.remote.ApiClient
import com.example.movieapplication.data.repository.MovieRepositoryImpl
import com.example.movieapplication.data.database.UserDatabase
import com.example.movieapplication.data.local.LocalDataStore
import com.example.movieapplication.domain.repository.MovieRepository
import com.example.movieapplication.domain.repository.UserRepository
import com.example.movieapplication.domain.usecase.MovieUseCase
import com.example.movieapplication.presentation.viewModel.AuthViewModel
import com.example.movieapplication.presentation.viewModel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val dataModule
        get() = module {
            single { ApiClient.create() }
            single { LocalDataStore(get()) }
            single {
                UserDatabase.getInstance(androidContext()).userDao
            }
            factory<MovieRepository>{MovieRepositoryImpl(get())}
            factory { UserRepository(get(), get()) }
            factory { MovieUseCase(get()) }

        }


    val uiModule
        get() = module {
            viewModel { AuthViewModel(get()) }
            viewModel { MovieViewModel(get()) }
        }

}
