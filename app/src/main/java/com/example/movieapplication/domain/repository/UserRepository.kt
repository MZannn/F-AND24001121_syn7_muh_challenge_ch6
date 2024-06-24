package com.example.movieapplication.domain.repository

import com.example.movieapplication.data.database.dao.UserDao
import com.example.movieapplication.data.local.LocalDataStore
import com.example.movieapplication.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userDao: UserDao,
    private val localDataStore: LocalDataStore
) {

    val userFlow: Flow<User?> = localDataStore.getUser()
    fun register(user: User) {
        userDao.insert(user)
    }

    suspend fun saveUser(user: User) {
        localDataStore.setUserLogin(user)
    }

    suspend fun clearUser() {
        localDataStore.clearUser()
    }

    suspend fun getUserById(id: Int): Flow<User?> {
        return flow {
            emit(userDao.getUserById(id))
        }
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(
            username = user.username,
            fullname = user.fullname!!,
            birthdate = user.birthdate!!,
            address = user.address!!,
            photo = user.photo!!,
            id = user.id!!
        )
        saveUser(user)
    }

    fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

     suspend fun getLogin(): Boolean {
        return localDataStore.getLogin().first()
    }
}
