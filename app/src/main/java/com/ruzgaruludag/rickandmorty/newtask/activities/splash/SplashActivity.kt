package com.ruzgaruludag.rickandmorty.newtask.activities.splash

import android.content.Intent
import android.os.Bundle
import com.ruzgaruludag.rickandmorty.newtask.base.BaseActivity
import com.ruzgaruludag.rickandmorty.databinding.ActivitySplashBinding
import com.ruzgaruludag.rickandmorty.newtask.activities.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    // TODO: 18.02.2024 properties
    private lateinit var bind: ActivitySplashBinding


    // TODO: 18.02.2024 lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bind.root)


        launchWithDelay(2000){
            val startIntent = Intent(this, HomeActivity::class.java)
            startActivity(startIntent)
        }
    }

    // TODO: 18.02.2024 functions
}