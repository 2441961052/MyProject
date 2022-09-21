package com.app.weather.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.databinding.ActivityDetailBinding
import com.app.weather.ui.adapter.DetailsAdapter
import com.app.weather.ui.adapter.SearchAdapter

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val date = intent.getStringExtra("date")
        val msg = (intent.getSerializableExtra("msg") as MutableMap<String, Any>)
        binding.tvTitle.text = date
        msg.let {
            binding.container.apply {
                setHasFixedSize(true)
                msg.remove("date")
                layoutManager = LinearLayoutManager(context)
                adapter = DetailsAdapter(this@DetailActivity, msg)
            }
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}