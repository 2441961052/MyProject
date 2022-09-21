package com.example.im.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.im.app.IMApplication

class BaseReciver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_TIME_TICK->{
                Toast.makeText(IMApplication.instance,"时间变化",Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_SCREEN_OFF->{
                Toast.makeText(IMApplication.instance,"锁屏",Toast.LENGTH_SHORT).show()
            }
        }
    }
}