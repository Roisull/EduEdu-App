package com.example.eduedu

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    // instance waktu untuk berpindah activity otomatis
    private val SPLASH_TIME_OUT:Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // berpindah activity secara otomatis
        Handler().postDelayed({

            val intentMain = Intent(this,OnBoardingActivity::class.java)
            startActivity(intentMain)
            finish()

        }, SPLASH_TIME_OUT)

    }
}