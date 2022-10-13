package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.interceptor.AuthInterceptor
import com.example.weatherapp.data.interceptor.AuthInterceptor2
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private lateinit var apiService: ApiService
        private lateinit var apiService2 : ApiService

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
        fun getCitiesService(): ApiService {
            if (!::apiService2.isInitialized) {
                val request = Retrofit.Builder()
                    .baseUrl("https://wft-geo-db.p.rapidapi.com/v1/geo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient2())
                    .build()
                apiService2 = request.create(ApiService::class.java)

            }
            return apiService2
        }

        private fun getHttpClient(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())
            httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()
        }
        private fun getHttpClient2(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor2())
            httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            httpClient.writeTimeout(90, java.util.concurrent.TimeUnit.SECONDS)
            return httpClient.build()
        }
    }
}

