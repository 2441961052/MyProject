package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import kotlinx.android.synthetic.main.view_sendmsg_item_view.view.*
import java.util.*

class SendMsgItemView(context: Context?, attrs: AttributeSet?=null) : RelativeLayout(context, attrs) {
    fun bindView(emMessage: EMMessage, showTime: Boolean) {
        updateTime(emMessage,showTime)
        updateMsg(emMessage)
    }

    private fun updateMsg(emMessage: EMMessage) {
        if (emMessage.type==EMMessage.Type.TXT){
            tv_msg.text=(emMessage.body as EMTextMessageBody).message
        }else{
            tv_msg.text=context.getString(R.string.noTXTmsg)
        }
    }

    private fun updateTime(emMessage: EMMessage, showTime: Boolean) {
        if (showTime) {
            tv_time.visibility = View.VISIBLE
            tv_time.text=com.hyphenate.util.DateUtils.getTimestampString(Date(emMessage.msgTime))
        }else{
            tv_time.visibility = View.GONE
        }
    }

    init {
        View.inflate(context, R.layout.view_sendmsg_item_view,this)
    }
}