package com.renuyadav.dig_it.home

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.renuyadav.dig_it.R
import com.renuyadav.dig_it.databinding.ActivitySplashLayoutBinding
import java.util.*

class Splash : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySplashLayoutBinding>(this, R.layout.activity_splash_layout,null)
        moveToHome()
    }
    private fun moveToHome(){
        Timer().schedule(object :TimerTask(){
            override fun run() {
                FeedPage.startActivity(this@Splash)
                finish()
            }
        },4000)
    }
}