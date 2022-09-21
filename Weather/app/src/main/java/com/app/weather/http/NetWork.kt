package com.app.weather.http

import com.app.network.http.ResponseAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object NetWork {
    private val apiService = ServiceCreator.create(ApiService::class.java)

    suspend fun getDayInfo(city:String) = apiService.getDayInfo("22152823","Xu7xMwcH",city).await()

    suspend fun getWeekInfo(city: String) = apiService.getWeekInfo("22152823","Xu7xMwcH",city).await()


    private fun stringToBody(string: String): RequestBody {
        val type: MediaType? = MediaType.parse("application/json")
        return RequestBody.create(type, string)
    }
    private fun mapToBody(map: MutableMap<String,Any>):RequestBody{
        val type: MediaType? = MediaType.parse("application/json; charset=UTF-8")
        val data: String = ObjectMapper().writeValueAsString(map)
        return RequestBody.create(type, data)
    }

    private suspend fun <T> Call<T>.await(): ResponseAdapter<T> {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body() as T
                    var code = response.code()
                    if(code == 200){
                        continuation.resume(ResponseAdapter(body,true,""))
                    }else{
                        continuation.resume(ResponseAdapter(null,false,"error-code:$code"))
                    }
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resume(ResponseAdapter(null,false,t.message.toString()))
                }
            })
        }
    }
}