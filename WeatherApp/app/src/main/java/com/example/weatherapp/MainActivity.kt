package com.example.weatherapp.views

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.api.ApiClient
import com.example.weatherapp.data.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : Activity() {
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

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_weather)
        getApiService()
        nextDay1 = findViewById(R.id.nextDay1)
        nextDay2 = findViewById(R.id.nextDay2)
        nextDay3 = findViewById(R.id.nextDay3)
        cityText = findViewById(R.id.cityText)
        tempText = findViewById(R.id.tempText)
        nextDay1Day = findViewById(R.id.nextDay1Day)
        nextDay2Day = findViewById(R.id.nextDay2Day)
        nextDay3Day = findViewById(R.id.nextDay3Day)
        nextDay1Nigth = findViewById(R.id.nextDay1Nigth)
        nextDay2Nigth = findViewById(R.id.nextDay2Nigth)
        nextDay3Nigth = findViewById(R.id.nextDay3Nigth)
        tempIcon = findViewById(R.id.tempIcon)
        nextDay1Icon = findViewById(R.id.nextDay1Icon)
        nextDay2Icon = findViewById(R.id.nextDay2Icon)
        nextDay3Icon = findViewById(R.id.nextDay3Icon)
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_Y,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 3000



            // Run your animation.
            slideUp.start()

        }


        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]

        when (day) {
            Calendar.SUNDAY -> {
                nextDay1.text = "Monday"
                nextDay2.text = "Tuesday"
                nextDay3.text = "Wednesday"
            }
            Calendar.MONDAY -> {
                nextDay1.text = "Tuesday"
                nextDay2.text = "Wednesday"
                nextDay3.text = "Thursday"
            }
            Calendar.TUESDAY -> {
                nextDay1.text = "Wednesday"
                nextDay2.text = "Thursday"
                nextDay3.text = "Friday"
            }
            Calendar.WEDNESDAY -> {
                nextDay1.text = "Thursday"
                nextDay2.text = "Friday"
                nextDay3.text = "Saturday"
            }
            Calendar.THURSDAY -> {
                nextDay1.text = "Friday"
                nextDay2.text = "Saturday"
                nextDay3.text = "Sunday"
            }
            Calendar.FRIDAY -> {
                nextDay1.text = "Saturday"
                nextDay2.text = "Sunday"
                nextDay3.text = "Monday"
            }
            Calendar.SATURDAY -> {
                nextDay1.text = "Sunday"
                nextDay2.text = "Monday"
                nextDay3.text = "Tuesday"
            }

        }

    }

    private fun getApiService() {
        ApiClient.getApiService().getWeathers("41.01384","28.94966","tr","metric").enqueue(object : Callback<WeatherModel> {
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                if (response.isSuccessful) {
                    Log.d("DENEME", "onResponse: ${response.body()?.daily}")
                    val weathers = response.body()
                    weathers?.let {
                        cityText.text = response.body()!!.timezone
                        tempText.text = response.body()!!.current?.temp?.toInt().toString() + "°C"
                        Glide.with(this@MainActivity).load("http://openweathermap.org/img/wn/" + response.body()!!.current?.weather?.get(0)?.icon + ".png").into(tempIcon)
                        nextDay1Day.text = response.body()!!.daily[1].temp.day.toInt().toString()+ "°C"
                        nextDay2Day.text = response.body()!!.daily[2].temp.day.toInt().toString()+ "°C"
                        nextDay3Day.text = response.body()!!.daily[3].temp.day.toInt().toString()+ "°C"
                        nextDay1Nigth.text = response.body()!!.daily[1].temp.night.toInt().toString()+ "°C"
                        nextDay2Nigth.text = response.body()!!.daily[2].temp.night.toInt().toString()+ "°C"
                        nextDay3Nigth.text = response.body()!!.daily[3].temp.night.toInt().toString()+ "°C"
                        Glide.with(this@MainActivity).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[1].weather?.get(0)?.icon + ".png").into(nextDay1Icon)
                        Glide.with(this@MainActivity).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[2].weather?.get(0)?.icon + ".png").into(nextDay2Icon)
                        Glide.with(this@MainActivity).load("http://openweathermap.org/img/wn/" + response.body()!!.daily[3].weather?.get(0)?.icon + ".png").into(nextDay3Icon)
                    }
                }
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("DENEME", "onResponse: ${t.message}")

            }
        })
    }
    }



