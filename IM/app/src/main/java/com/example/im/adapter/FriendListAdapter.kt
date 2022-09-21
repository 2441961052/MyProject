package com.example.im.adapter

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.im.R
import com.example.im.data.FriendListItem
import com.example.im.v.activity.AddFriendActivity
import com.example.im.v.activity.ChatActivity
import com.example.im.widget.FriendListItemView
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class FriendListAdapter(val context: Context, var list: MutableList<FriendListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FriendListItemViewHolder(FriendListItemView(parent.context))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView as FriendListItemView
        itemView.bindView(list[position])
        val username = list.get(position).name
        itemView.setOnClickListener { context.startActivity<ChatActivity>("username" to username) }
        itemView.setOnLongClickListener {
            AlertDialog.Builder(context)
                .setTitle(R.string.deleteFriend)
                .setMessage("是否删除$username")
                .setNegativeButton(R.string.cancelDelete, null)
                .setPositiveButton(R.string.isDelete, DialogInterface
                    .OnClickListener { dialog, which ->
                        deleteFriend(username)
                    }).show()
            true
        }
    }

    private fun deleteFriend(username: String) {
        EMClient.getInstance().contactManager()
            .asyncDeclineInvitation(username, object : EMCallBackAdater() {
                override fun onError(code: Int, error: String?) {
                    context.runOnUiThread { toast(R.string.deleteFailed) }
                }

                override fun onSuccess() {
                    context.runOnUiThread { toast(R.string.deleteSuccess) }
                }
            })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FriendListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}