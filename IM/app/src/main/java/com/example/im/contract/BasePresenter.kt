package com.example.im.contract

import android.os.Handler
import android.os.Looper


/*
        后面用来做一些Presenter的拓展
 */
interface BasePresenter {
    companion object{
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }
    fun uiThread(f:()->Unit){
        handler.post { f() }
    }
}