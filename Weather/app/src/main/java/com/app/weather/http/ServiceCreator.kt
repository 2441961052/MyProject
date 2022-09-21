package com.app.weather.http
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {
    private const val BASE_URL = "https://yiketianqi.com/free/"
    val retrofit = buildRetrofit( buildClient() )
    val tag = "http_info"
    private fun buildClient(): OkHttpClient {
        /*
         **打印retrofit信息部分
        */
        val loggingInterceptor = HttpLoggingInterceptor { message -> //打印retrofit日志
            Log.e(tag," = $message")
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                val builder = request.newBuilder()
                //.addHeader("X-Requested-With","XMLHttpRequest")
                //.addHeader("Content-Type","application/json")
                //.cacheControl(cacheControl)
                //.addHeader("Connection", "close")
                //.cacheControl(CacheControl.FORCE_NETWORK)
                request = builder.build()
                chain.proceed(request)
            }
        builder.addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
        return builder.build()
    }

    private fun buildRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create()) //转换为字符串
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}