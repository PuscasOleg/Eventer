package com.example.eventer.SplashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.eventer.MainActivity
import com.example.eventer.R


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var   splash: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        splash= Handler()
        splash.postDelayed({
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}