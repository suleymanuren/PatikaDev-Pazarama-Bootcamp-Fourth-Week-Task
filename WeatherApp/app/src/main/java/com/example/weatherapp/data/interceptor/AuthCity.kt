package com.example.weatherapp.data.interceptor

import com.example.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor2 : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val apiKeyRequest = originalRequest
            .newBuilder()
            .get()
            .addHeader("X-RapidAPI-Key", BuildConfig.CITY_KEY)
            .build()
        return chain.proceed(apiKeyRequest)
    }
}

