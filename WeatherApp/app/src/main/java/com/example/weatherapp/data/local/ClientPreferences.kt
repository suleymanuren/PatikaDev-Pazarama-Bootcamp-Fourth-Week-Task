package com.example.weatherapp.data.local

import androidx.fragment.app.FragmentActivity


class ClientPreferences(context: FragmentActivity?) {
    private val PREFS_FILE_NAME = "client_preferences"
    private val prefs = context?.getSharedPreferences(PREFS_FILE_NAME, 0)

    companion object {
        const val CITY_LAT = "KEY_CITY_LAT"
        const val CITY_LONG = "KEY_CITY_LONG"
        const val CITY_NAME = "KEY_CITY_NAME"

    }
    fun setCityName(lat: String) {
        prefs?.edit()?.putString(CITY_NAME,lat)?.apply()
    }

    fun getCityName(): String? {
        return prefs?.getString(CITY_NAME, null)
    }
    fun setCityLatitude(lat: String) {
        prefs?.edit()?.putString(CITY_LAT,lat)?.apply()
    }

    fun getCityLatitude(): String? {
        return prefs?.getString(CITY_LAT, null)
    }

    fun setCityLongitude(long: String) {
        prefs?.edit()?.putString(CITY_LONG, long)?.apply()
    }

    fun getCityLongitude(): String? {
        return prefs?.getString(CITY_LONG, null)
    }

}