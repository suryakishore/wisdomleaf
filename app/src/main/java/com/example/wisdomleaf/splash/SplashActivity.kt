package com.example.wisdomleaf.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.wisdomleaf.MainActivity
import com.example.wisdomleaf.R


/**
 * splash activity for the app this will appear only for 5 seconds by using handler.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(
            object : Runnable {
                override fun run() {
                    startHomePage()
                }
            },
            5000
        )
    }

    /**
     * This method is used to open the main activity
     */
    private fun startHomePage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}