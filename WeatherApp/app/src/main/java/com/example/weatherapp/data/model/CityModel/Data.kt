package com.example.weatherapp.data.model.CityModel

data class Data(
    val city: String,
    val country: String,
    val countryCode: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int,
    val region: String,
    val regionCode: String,
    val type: String,
    val wikiDataId: String
)