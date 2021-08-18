package com.enigmacamp.simplesharedpref.data.repository

import com.enigmacamp.simplesharedpref.data.request.AuthenticationRequest

interface AuthenticationRepository {
    suspend fun login(authenticationRequest: AuthenticationRequest): Boolean

    companion object {
        const val TOKEN = "token"
    }
}