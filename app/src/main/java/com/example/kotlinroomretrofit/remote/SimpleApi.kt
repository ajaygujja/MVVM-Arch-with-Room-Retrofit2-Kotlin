package com.example.kotlinroomretrofit.remote

import com.example.kotlinroomretrofit.model.User
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("users")
    suspend fun getUser(): Response<List<User>>


}