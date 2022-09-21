package com.app.weather.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.weather.databinding.ActivitySearchBinding
import com.app.weather.http.NetWork
import com.app.weather.ui.adapter.SearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.util.stream.Collectors

class SearchActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchBinding
    lateinit var city:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBtn.setOnClickListener {
            city = binding.searchEtCityName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {//IO 协程、不阻塞主进程
                val response = NetWork.getDayInfo(city)
                if(response.status){
                    val result = response.result
                    result?.let {
                        withContext(Dispatchers.Main){//切换到主进程
                            val list = result.keys.stream().collect(Collectors.toList())
                            binding.container.apply {
                                setHasFixedSize(true)
                                layoutManager = LinearLayoutManager(context)
                                adapter = SearchAdapter(this@SearchActivity,result)
                            }
                        }
                    }
                }else{
                    withContext(Dispatchers.Main) {//切换到主进程
                        Toast.makeText(this@SearchActivity, response.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.searchDetail.setOnClickListener {
            city = binding.searchEtCityName.text.toString()
            CoroutineScope(Dispatchers.IO).launch {//IO 协程、不阻塞主进程
                val response = NetWork.getWeekInfo(city)
                if(response.status){
                    val result = response.result
                    result?.let {
                        val data = result["data"] as MutableList<MutableMap<String,Any>>
                        withContext(Dispatchers.Main) {//切换到主进程
                            val intent= Intent(this@SearchActivity,SelectActivity::class.java)
                            intent.putExtra("map",data as Serializable)
                            startActivity(intent)
                        }
                    }
                }else{
                    withContext(Dispatchers.Main) {//切换到主进程
                        Toast.makeText(this@SearchActivity, response.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

}