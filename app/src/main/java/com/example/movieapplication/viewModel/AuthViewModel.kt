package com.example.movieapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapplication.model.User
import com.example.movieapplication.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userFlow: Flow<User?> = userRepository.userFlow
    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult
    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUser(user)
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            userRepository.clearUser()
        }
    }

    suspend fun getUserById(id: Int): Flow<User?> {
        return userRepository.getUserById(id)
    }

    fun updateUser(
        username: String,
        fullname: String,
        birthdate: String,
        address: String,
        photo: String?,
        id: Int
    ) {
        viewModelScope.launch {
            val currentUser = userRepository.getUserById(id).firstOrNull()
            if (currentUser != null) {
                val updatedUser = currentUser.copy(
                    username = username,
                    fullname = fullname,
                    birthdate = birthdate,
                    address = address,
                    photo = photo ?: currentUser.photo // Maintain current photo if not updated
                )
                userRepository.updateUser(updatedUser)
            }
        }
    }

    fun register(user: User) {
        viewModelScope.launch {
            userRepository.register(user)
        }
    }

    fun login(email: String, password: String): LiveData<User?> {

        viewModelScope.launch {
            val user = userRepository.login(email, password)
            _loginResult.postValue(user)
        }
        return _loginResult
    }

    suspend fun getLogin() :Boolean {
       return userRepository.getLogin()
    }
}
