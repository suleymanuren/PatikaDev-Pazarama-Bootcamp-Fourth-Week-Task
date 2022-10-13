package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.CityModel.CitiesModel
import com.example.weatherapp.data.model.WeatherModel.WeatherModel
import com.example.weatherapp.data.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ONECALL)
    fun getWeathers(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String,
        @Query("units") units: String,
        //@Query("exclude") exclude: String
    ): Call<WeatherModel>

@GET(Constants.ADMIN)
    fun getCities(
    @Query("countryIds") countryIds: String,
    ): Call<CitiesModel>

}