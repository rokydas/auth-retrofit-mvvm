package com.example.mvvm_auth.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_auth.data.network.Resource
import com.example.mvvm_auth.data.repository.AuthRepository
import com.example.mvvm_auth.data.responses.LoginResponse
import com.example.mvvm_auth.data.responses.UserInfo
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>>
        = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse


    fun login(
        userInfo: UserInfo
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(userInfo)
    }

}