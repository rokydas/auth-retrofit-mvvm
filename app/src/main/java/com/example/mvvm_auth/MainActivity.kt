package com.example.mvvm_auth

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.mvvm_auth.data.UserPreferences
import com.example.mvvm_auth.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val userPreferences = UserPreferences(this)
//
//        userPreferences.authToken.asLiveData().observe(this, Observer {
//            Toast.makeText(
//                this,
//                it ?: "Token is null",
//                Toast.LENGTH_LONG
//            ).show()
//            startActivity(Intent(this, AuthActivity::class.java))
//        })

        val sharedPreferences = getSharedPreferences("user-token", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")
        Toast.makeText(
                this,
                token,
                Toast.LENGTH_LONG
            ).show()

        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }
}