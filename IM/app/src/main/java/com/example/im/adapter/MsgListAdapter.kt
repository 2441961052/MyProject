package com.example.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.widget.ReceiveMsgItemView
import com.example.im.widget.SendMsgItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

class MsgListAdapter(val context: Context,val msg:List<EMMessage>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        val ITEM_SEND_MSG=0
        val ITEM_RECEIVE_MSG=1
    }

    override fun getItemViewType(position: Int): Int {
        if(msg[position].direct()==EMMessage.Direct.SEND){
            return ITEM_SEND_MSG
        }else{
            return ITEM_RECEIVE_MSG
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType== ITEM_SEND_MSG){
            return sendViewHolder(SendMsgItemView(context))
        }else{
            return receiveViewHolder(ReceiveMsgItemView(context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val showTime = isShowTime(position)
        if(getItemViewType(position)== ITEM_SEND_MSG){
            val sendMsgItemView = holder.itemView as SendMsgItemView
            sendMsgItemView.bindView(msg[position],showTime)
        }else{
            val receiveMsgItemView = holder.itemView as ReceiveMsgItemView
            receiveMsgItemView.bindView(msg[position],showTime)
        }
    }

    private fun isShowTime(position: Int): Boolean {
        var showTime = true
        if(position>0){
            showTime = !DateUtils.isCloseEnough(msg[position].msgTime,msg[position-1].msgTime)
        }
        return showTime
    }

    override fun getItemCount(): Int = msg.size

    class sendViewHolder(item:View):RecyclerView.ViewHolder(item){

    }
    class receiveViewHolder(item:View):RecyclerView.ViewHolder(item){

    }

}