package com.example.mvvm_auth.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.mvvm_auth.databinding.FragmentLoginBinding
import com.example.mvvm_auth.data.network.AuthApi
import com.example.mvvm_auth.data.network.Resource
import com.example.mvvm_auth.data.repository.AuthRepository
import com.example.mvvm_auth.data.responses.UserInfo
import com.example.mvvm_auth.ui.base.BaseFragment
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
//                    lifecycleScope.launch {
//                        userPreferences.saveAuthToken(it.value.token)
//                    }

                    val sharedPreferences = this.requireActivity().getSharedPreferences("user-token", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("token", it.value.token)
                    editor.apply()

                    binding.authText.text = it.toString()
                    Toast.makeText(
                        requireContext(),
                        it.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is Resource.Failure -> {
                    binding.authText.text = it.toString()
                    Toast.makeText(
                        requireContext(),
                        it.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()
            val userInfo : UserInfo = UserInfo(email, password)

            viewModel.login(userInfo)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository()
        = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}