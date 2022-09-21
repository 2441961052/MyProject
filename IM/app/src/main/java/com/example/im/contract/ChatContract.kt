package com.example.im.contract

import com.hyphenate.chat.EMMessage

interface ChatContract {
    interface Presenter:BasePresenter{
        fun sendMsg(context: String,msg:String)
        fun addMessage(username: String, messages: MutableList<EMMessage>?)
        fun loadMsg(username: String)
        fun loadMoreMsg(username: String)
    }
    interface View{


        fun startSendMsg()
        fun sendMsgSuccess()
        fun sendMsgFailed()
        fun msgLoaded()
        fun moreMsgLoad(size: Int)
    }
}