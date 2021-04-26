package com.example.kotlinroomretrofit.repository

import androidx.lifecycle.LiveData
import com.example.kotlinroomretrofit.Database.UserDao
import com.example.kotlinroomretrofit.model.User

class UserLocalRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}