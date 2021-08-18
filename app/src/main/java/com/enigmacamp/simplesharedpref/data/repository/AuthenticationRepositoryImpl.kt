package com.enigmacamp.simplesharedpref.data.repository

import com.enigmacamp.simplesharedpref.data.SharedPref
import com.enigmacamp.simplesharedpref.data.api.AuthApi
import com.enigmacamp.simplesharedpref.data.request.AuthenticationRequest

class AuthenticationRepositoryImpl(
    private val authApi: AuthApi,
    private val sharedPref: SharedPref
) :
    AuthenticationRepository {
    override suspend fun login(authenticationRequest: AuthenticationRequest): Boolean {
        val response = authApi.login(authenticationRequest)
        if (response.isSuccessful) {
            response.body()?.let {
                sharedPref.save(AuthenticationRepository.TOKEN, it.token)
                return true
            }
            return false
        }
        return false
    }
}