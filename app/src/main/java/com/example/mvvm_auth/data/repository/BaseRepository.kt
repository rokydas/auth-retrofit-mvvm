package com.example.mvvm_auth.data.repository

import android.util.Log
import com.example.mvvm_auth.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> SafeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Log.d("test", "message1")
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Log.d("test", throwable.message.toString())
                when(throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }

}