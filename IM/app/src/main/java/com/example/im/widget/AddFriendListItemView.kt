package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.example.im.adapter.EMCallBackAdater
import com.example.im.data.AddFriendListItem
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.view_addfriend_item.view.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

class AddFriendListItemView(context: Context?, attrs: AttributeSet?=null) :
    RelativeLayout(context, attrs) {
    fun bindView(addFriendListItem: AddFriendListItem) {
        if(addFriendListItem.isadded){
            add.isEnabled=false
            add.text=context.getString(R.string.notaddFriend)
        }else{
            add.isEnabled=true
            add.text=context.getString(R.string.addFriend)
        }
        userName.text = addFriendListItem.name
        creatData.text = addFriendListItem.time

        add.setOnClickListener { addFriend(addFriendListItem.name) }
    }

    private fun addFriend(name: String) {
        EMClient.getInstance().contactManager()
            .aysncAddContact(name,null, object :EMCallBackAdater(){
                override fun onSuccess() {
                    context.runOnUiThread {
                        toast("发送添加好友请求成功")
                    }
                }

                override fun onError(code: Int, error: String?) {
                    context.runOnUiThread {
                        toast("发送好友请求失败")
                    }
                }
        })
    }

    init {
            View.inflate(context, R.layout.view_addfriend_item,this)
    }
}