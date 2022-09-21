package com.example.im.factory

import androidx.fragment.app.Fragment
import com.example.im.R
import com.example.im.v.fragment.AboutFragment
import com.example.im.v.fragment.FriendFragment
import com.example.im.v.fragment.MessageFragment

class FragmentFactory private constructor(){
    val messageFragment by lazy { MessageFragment() }
    val friendFragment by lazy { FriendFragment() }
    val aboutFragment by lazy { AboutFragment() }
    companion object{
        val instance = FragmentFactory()
    }
    fun getFragment(id:Int):Fragment{
        when(id){
            R.id.item_message->return messageFragment
            R.id.item_friend->return friendFragment
            else->return aboutFragment
        }

    }
}