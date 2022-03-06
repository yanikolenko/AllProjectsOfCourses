package com.yaroslavnikolenko.quakereload.ui.SplashActivity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.yaroslavnikolenko.quakereload.MainActivity
import com.yaroslavnikolenko.quakereload.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private var activitySplashScreenBinding: ActivitySplashScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(activitySplashScreenBinding?.root)

        makeFullScreen()
        handler()

    }

    private fun handler(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }

    private fun makeFullScreen(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}