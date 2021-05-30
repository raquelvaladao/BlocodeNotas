package com.raquel.blocodenotas.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.raquel.blocodenotas.MainActivity
import com.raquel.blocodenotas.R
import java.util.concurrent.Executor

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

       Handler(Looper.getMainLooper()).postDelayed( {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)


    }
}