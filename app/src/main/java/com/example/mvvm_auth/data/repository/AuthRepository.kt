package com.example.mvvm_auth.data.repository

import com.example.mvvm_auth.data.network.AuthApi
import com.example.mvvm_auth.data.responses.UserInfo

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(
        userInfo: UserInfo
    ) = SafeApiCall {
        api.login(userInfo)
    }

}