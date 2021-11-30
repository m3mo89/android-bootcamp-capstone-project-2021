package com.wizeline.bootcamp.capstone

import android.content.Intent
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val SPLASH_SCREEN_MAX_DURATION = 2000L
        const val TIMER_STEP_TIME = 1000L
    }

    override fun onResume() {
        super.onResume()
        executeCountdown()
    }

    private fun executeCountdown() {
        val timer = object : CountDownTimer(SPLASH_SCREEN_MAX_DURATION, TIMER_STEP_TIME) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }

        }

        timer.start()
    }
}