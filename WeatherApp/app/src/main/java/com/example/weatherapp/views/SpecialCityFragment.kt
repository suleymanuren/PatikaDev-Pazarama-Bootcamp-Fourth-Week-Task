package com.example.weatherapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.api.ApiClient
import com.example.weatherapp.data.api.ApiClient.Companion.getApiService
import com.example.weatherapp.data.local.ClientPreferences
import com.example.weatherapp.data.local.DataStoreManager
import com.example.weatherapp.data.model.CityModel.CitiesModel
import com.example.weatherapp.data.model.WeatherModel.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class SpecialCityFragment : Fragment() {
    private lateinit var nextDay1 : TextView
    private lateinit var nextDay2 : TextView
    private lateinit var nextDay3 : TextView
    private lateinit var cityText : TextView
    private lateinit var tempText : TextView
    private lateinit var nextDay1Day : TextView
    private lateinit var nextDay2Day : TextView
    private lateinit var nextDay3Day : TextView
    private lateinit var nextDay1Nigth : TextView
    private lateinit var nextDay2Nigth : TextView
    private lateinit var nextDay3Nigth : TextView
    private lateinit var tempIcon : ImageView
    private lateinit var nextDay1Icon : ImageView
    private lateinit var nextDay2Icon : ImageView
    private lateinit var nextDay3Icon : ImageView
    private lateinit var navController: NavController
    private lateinit var clientPreferences: ClientPreferences
    private lateinit var dataStoreManager: DataStoreManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupViews(view)
        arguments?.let {
            val cityLat = it.getString("cityLat")
            val cityLong= it.getString("cityLong")
            cityText.text = it.getString("cityName")

            Log.d("DENEME", "${cityLat} ${cityLong}")

            getApiService(cityLat.toString(),cityLong.toString())
            getNext3Day()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_special_city, container, false)
    }

    private fun setupViews(view: View) {

        clientPreferences = ClientPreferences(activity)
        dataStoreManager = DataStoreManager(activity)
        nextDay1 = view.findViewById(R.id.nextDay1)
        nextDay2 = view.findViewById(R.id.nextDay2)
        nextDay3 = view.findViewById(R.id.nextDay3)
        cityText = view.findViewById(R.id.cityText)
        tempText = view.findViewById(R.id.tempText)
        nextDay1Day = view.findViewById(R.id.nextDay1Day)
        nextDay2Day = view.findViewById(R.id.nextDay2Day)
        nextDay3Day = view.findViewById(R.id.nextDay3Day)
        nextDay1Nigth = view.findViewById(R.id.nextDay1Nigth)
        nextDay2Nigth = view.findViewById(R.id.nextDay2Nigth)
        nextDay3Nigth = view.findViewById(R.id.nextDay3Nigth)
        tempIcon = view.findViewById(R.id.tempIcon)
        nextDay1Icon = view.findViewById(R.id.nextDay1Icon)
        nextDay2Icon = view.findViewById(R.id.nextDay2Icon)
        nextDay3Icon = view.findViewById(R.id.nextDay3Icon)


    }
    // MANUALLY WRITING NEXT 3 DAYS TO SCREEN
    private fun getNext3Day(){

        val calendar = Calendar.getInstance()
        val dayInt = calendar[Calendar.DAY_OF_WEEK]
        when (dayInt) {
            7-> {
                nextDay1.text = "Monday"
                nextDay2.text = "Tuesday"
                nextDay3.text = "Wednesday"
            }

            1 -> {
                nextDay1.text = "Tuesday"
                nextDay2.text = "Wednesday"
                nextDay3.text = "Thursday"
            }
            2 -> {
                nextDay1.text = "Wednesday"
                nextDay2.text = "Thursday"
                nextDay3.text = "Friday"
            }
            3 -> {
                nextDay1.text = "Thursday"
                nextDay2.text = "Friday"
                nextDay3.text = "Saturday"
            }
            4 -> {
                nextDay1.text = "Friday"
                nextDay2.text = "Saturday"
                nextDay3.text = "Sunday"
            }
            5-> {
                nextDay1.text = "Saturday"
                nextDay2.text = "Sunday"
                nextDay3.text = "Monday"
            }
            6 -> {
                nextDay1.text = "Sunday"
                nextDay2.text = "Monday"
                nextDay3.text = "Tuesday"
            }

        }
    }
    // GETTING WEATHER DATA FROM API
    private fun getApiService(lat : String, lon : String ) {
        Log.d("DENEME2", "${Calendar.getInstance()[Calendar.DAY_OF_WEEK]}")
        ApiClient.getApiService().getWeathers(lat,lon,"tr","metric").enqueue(object :
            Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    val weathers = response.body()
                    weathers?.let {
                        tempText.text = response.body()!!.current?.temp?.toInt().toString() + "°C"
                        Glide.with(this@SpecialCityFragment).load("http://openweathermap.org/img/wn/" + response.body()!!.current?.weather?.get(0)?.icon + ".png").into(tempIcon)
                        nextDay1Day.text = response.body()!!.daily[1].temp.day.toInt().toString()+ "°C"
                        nextDay2Day.text = response.body()!!.daily[2].temp.day.toInt().toString()+ "°C"
                        nextDay3Day.text = response.body()!!.daily[3].temp.day.toInt().toString()+ "°C"
                        nextDay1Nigth.text = response.body()!!.daily[1].temp.night.toInt().toString()+ "°C"
                        nextDay2Nigth.text = response.body()!!.daily[2].temp.night.toInt().toString()+ "°C"
                        nextDay3Nigth.text = response.body()!!.daily[3].temp.night.toInt().toString()+ "°C"
                        Glide.with(this@SpecialCityFragment).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[1].weather?.get(0)?.icon + ".png").into(nextDay1Icon)
                        Glide.with(this@SpecialCityFragment).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[2].weather?.get(0)?.icon + ".png").into(nextDay2Icon)
                        Glide.with(this@SpecialCityFragment).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[3].weather?.get(0)?.icon + ".png").into(nextDay3Icon)
                    }
                }
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("DENEME", "onResponse: ${t.message}")
            }
        })
    }
}