package com.example.mvvm_auth.data.responses

data class LoginResponse(
    val __v: Int,
    val _id: String,
    val date: String,
    val email: String,
    val name: String,
    val password: String,
    val token: String
)