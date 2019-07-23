package com.example.cityguide

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class SplashScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(2000)

                    val intent = Intent(baseContext, MapsActivity::class.java)
                    startActivity(intent)
                }catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}

