package com.example.movieapplication.repository

import com.example.movieapplication.db.dao.UserDao
import com.example.movieapplication.helper.MyDataStore
import com.example.movieapplication.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userDao: UserDao,
    private val myDataStore: MyDataStore
) {

    val userFlow: Flow<User?> = myDataStore.getUser()
    suspend fun register(user: User) {
        userDao.insert(user)
    }

    suspend fun saveUser(user: User) {
        myDataStore.saveUser(user)
    }

    suspend fun clearUser() {
        myDataStore.clearUser()
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

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

     suspend fun getLogin(): Boolean {
        return myDataStore.getLogin().first()
    }
}
