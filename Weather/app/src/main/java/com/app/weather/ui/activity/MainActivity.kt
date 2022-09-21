package com.app.weather.ui.activity

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import com.app.weather.R
import com.app.weather.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val animation = ValueAnimator.ofInt(0,1000)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation.apply {
            duration = 3000
            addUpdateListener {
                val progress = it.animatedValue as Int
                binding.pbEnter.progress = progress
            }
            addListener(onEnd = {
                var intent= Intent(this@MainActivity,SearchActivity::class.java)
                startActivity(intent)
                finish()
            })
            start()
        }


        /*
        CoroutineScope(Dispatchers.IO).launch {
            for(index in 0..1000){
                binding.pbEnter.progress = index
                delay(3)
            }
            var intent= Intent(this@MainActivity,SearchActivity::class.java)
            startActivity(intent)
            finish()
        }

         */

    }

}