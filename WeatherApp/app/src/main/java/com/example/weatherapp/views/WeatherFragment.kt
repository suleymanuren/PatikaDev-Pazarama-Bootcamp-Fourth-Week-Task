package com.example.weatherapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import java.util.*


class WeatherFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setupViews(view)
    }

    private fun setupViews(view: View) {
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


}