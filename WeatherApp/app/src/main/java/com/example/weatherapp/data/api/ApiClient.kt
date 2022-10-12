package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private lateinit var apiService: ApiService

        fun getApiService(): ApiService {
            if (!::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService
        }

        private fun getHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()
        }
    }
}

