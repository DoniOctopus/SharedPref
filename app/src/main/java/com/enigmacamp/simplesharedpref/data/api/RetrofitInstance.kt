package com.enigmacamp.simplesharedpref.data.api

import com.enigmacamp.simplesharedpref.data.SharedPref
import com.enigmacamp.simplesharedpref.data.interceptor.AuthTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance(private val sharedPref: SharedPref) {
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(AuthTokenInterceptor(sharedPref))
            .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3008/enigma/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }
}