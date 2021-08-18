package com.enigmacamp.simplesharedpref.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enigmacamp.simplesharedpref.data.repository.AuthenticationRepository
import com.enigmacamp.simplesharedpref.data.request.AuthenticationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {

    fun doLogin(userName: String, userPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isAuthenticated = authenticationRepository.login(
                AuthenticationRequest(userName, userPassword)
            )
            Log.d("SharedPref", "doLogin: $isAuthenticated")
        }
    }
}