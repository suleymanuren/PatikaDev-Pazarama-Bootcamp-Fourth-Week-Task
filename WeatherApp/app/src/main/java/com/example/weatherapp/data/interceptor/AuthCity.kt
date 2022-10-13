package com.example.weatherapp.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor2 : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKeyRequest = originalRequest
            .newBuilder()
            .get()
            .addHeader("X-RapidAPI-Key", "7413a80555msh6798e4d45e32d3fp1278a7jsn3953d92a9b4b")
            .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
            .build()
        return chain.proceed(apiKeyRequest)
    }
}

