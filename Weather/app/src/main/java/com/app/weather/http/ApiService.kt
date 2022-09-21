package com.app.weather.http

import com.app.weather.model.DayInfo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("day/")
//    fun getDayInfo(
//        @Query("appid") appid: String,
//        @Query("appsecret") appsecret: String,
//        @Query("city") city: String
//    ):Call<DayInfo>
    fun getDayInfo(
        @Query("appid") appid: String,
        @Query("appsecret") appsecret: String,
        @Query("city") city: String="成都"
    ):Call<MutableMap<String,Any>>

    @GET("week/")
    fun getWeekInfo(
        @Query("appid") appid: String,
        @Query("appsecret") appsecret: String,
        @Query("city") city: String="成都"
    ):Call<MutableMap<String,Any>>

}