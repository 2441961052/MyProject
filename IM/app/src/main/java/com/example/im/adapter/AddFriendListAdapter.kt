package com.example.im.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im.data.AddFriendListItem
import com.example.im.widget.AddFriendListItemView

class AddFriendListAdapter(val context: Context, val addFriendItems: MutableList<AddFriendListItem>)
        : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return addFriendListitemViewHolder(AddFriendListItemView(context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val addFriendListItemView = holder.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[position])
    }

    override fun getItemCount(): Int = addFriendItems.size

    class addFriendListitemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}