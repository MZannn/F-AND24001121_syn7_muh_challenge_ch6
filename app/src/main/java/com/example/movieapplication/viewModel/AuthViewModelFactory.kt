package com.example.movieapplication.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapplication.db.UserDatabase
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.repository.UserRepository

class AuthViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: AuthViewModelFactory? = null

        fun getInstance(context: Context): AuthViewModelFactory {
            return instance ?: synchronized(this) {
                val userDao = UserDatabase.getInstance(context).userDao
                val myDataStore = MyDataStore(context)
                val userRepository = UserRepository(userDao, myDataStore)
                instance ?: AuthViewModelFactory(userRepository).also { instance = it }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
