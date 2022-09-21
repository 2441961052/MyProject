package com.app.weather.ui.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.databinding.ActivitySelectBinding
import com.app.weather.ui.adapter.SelectAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectActivity : AppCompatActivity() {
    var list = mutableListOf<MutableMap<String, Any>>()
    var mapDate = mutableMapOf<String,MutableMap<String,Any>>()
    var listDate = mutableListOf<String>()
    lateinit var binding: ActivitySelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = (intent.getSerializableExtra("map") as MutableList<MutableMap<String, Any>>)
        CoroutineScope(Dispatchers.IO).launch {
//            for (i in 0 until list.size) {
//                val date = list[i].get("date").toString()
//                mapDate.put(date,list[i])
//                listDate.add(date)
//            }
            withContext(Dispatchers.Main) {
                binding.container.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = SelectAdapter(this@SelectActivity,list)
                }
            }
        }
        binding.close.setOnClickListener { finish() }

    }
}