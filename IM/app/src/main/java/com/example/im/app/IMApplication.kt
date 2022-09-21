package com.example.im.app

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import cn.bmob.v3.Bmob
import com.example.im.demo.BaseReciver
import com.hyphenate.EMContactListener
import com.hyphenate.chat.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import org.jetbrains.anko.toast


class IMApplication :Application() {
    companion object{
        lateinit var instance:IMApplication
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        EMClient.getInstance().init(applicationContext, EMOptions())
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG)
        Bmob.initialize(this,"5127e5a6362f3a9a1e7537c90f957bd1")
        val reciver = BaseReciver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_TIME_TICK)
        registerReceiver(reciver,filter)

        EMClient.getInstance().contactManager().setContactListener(object : EMContactListener {
            //The contact request is approved
            override fun onFriendRequestAccepted(username: String) {

            }

            //contact request is rejected
            override fun onFriendRequestDeclined(username: String) {

            }

            //Received contact invitation
            override fun onContactInvited(username: String, reason: String) {
                EMClient.getInstance().contactManager().acceptInvitation(username)
                toast("$username --- $reason 联系人申请")
            }

            //Call back this method when deleted
            override fun onContactDeleted(username: String) {

            }

            //Call back this method when a contact is added
            override fun onContactAdded(username: String) {

            }
        })
    }
}