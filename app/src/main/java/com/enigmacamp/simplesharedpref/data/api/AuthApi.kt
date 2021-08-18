package com.enigmacamp.simplesharedpref.data.api

import com.enigmacamp.simplesharedpref.data.request.AuthenticationRequest
import com.enigmacamp.simplesharedpref.data.response.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body authentication: AuthenticationRequest):
            Response<AuthenticationResponse>
}