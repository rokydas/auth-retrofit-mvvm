package com.example.mvvm_auth.data.network

import com.example.mvvm_auth.data.responses.LoginResponse
import com.example.mvvm_auth.data.responses.UserInfo
import retrofit2.http.*

interface AuthApi {


    @POST("login")
    suspend fun login(
        @Body post: UserInfo
    ) : LoginResponse

}