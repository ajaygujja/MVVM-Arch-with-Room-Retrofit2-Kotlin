package com.example.kotlinroomretrofit.repository

import com.example.kotlinroomretrofit.model.User
import com.example.kotlinroomretrofit.remote.RetrofitInstance
import retrofit2.Response

class UserRepository {

    suspend fun getUser(): Response<List<User>> {
        return RetrofitInstance.api.getUser()
    }
}