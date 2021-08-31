package com.example.mvvm_auth.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_auth.data.repository.AuthRepository
import com.example.mvvm_auth.data.repository.BaseRepository
import com.example.mvvm_auth.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java)
                -> AuthViewModel(repository as AuthRepository ) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}