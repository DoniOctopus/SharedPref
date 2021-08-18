package com.enigmacamp.simplesharedpref.data.interceptor

import com.enigmacamp.simplesharedpref.data.SharedPref
import com.enigmacamp.simplesharedpref.data.repository.AuthenticationRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val sharedPref: SharedPref) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        if (!originRequest.url().toString().contains("login")) {
            sharedPref.retrived(AuthenticationRepository.TOKEN)?.let {
                val requestBuilder = originRequest.newBuilder()
                    .header("Authorization", it)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
            return chain.proceed(originRequest)
        }
        return chain.proceed(originRequest)
    }
}