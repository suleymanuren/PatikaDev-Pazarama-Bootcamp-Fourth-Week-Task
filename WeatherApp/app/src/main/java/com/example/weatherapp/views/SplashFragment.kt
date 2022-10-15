package com.example.weatherapp.views

import ConnectionLiveData
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R


class SplashFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var logo : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        logo = view.findViewById(R.id.iv_logo)
        val slideAnimation = AnimationUtils.loadAnimation(context, R.anim.slide)
        logo.startAnimation(slideAnimation)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        Handler().postDelayed({
            navController.navigate(R.id.action_splashFragment_to_weatherFragment)} , 3000)
        return view
    }
}