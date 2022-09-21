package com.example.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.im.R
import com.example.im.data.FriendListItem
import kotlinx.android.synthetic.main.view_friend_item.view.*

class FriendListItemView(context: Context?, attrs: AttributeSet?=null) :
                RelativeLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.view_friend_item,this)
    }
    fun bindView(friendListItem: FriendListItem){
        if(friendListItem.showFirstChar){
            FirstLetter.visibility=View.VISIBLE
            FirstLetter.text=friendListItem.firstCahr
        }else FirstLetter.visibility=View.GONE
        userName.text=friendListItem.name
    }
}