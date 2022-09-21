package com.example.im.presenter_M

import com.example.im.contract.FriendContract
import com.example.im.data.FriendListItem
import com.example.im.data.db.Friend
import com.example.im.data.db.IMDatabase
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.collections.forEachByIndex
import org.jetbrains.anko.doAsync


class FriendPresenter(val view: FriendContract.View) : FriendContract.presenter {
    val constrains = mutableListOf<FriendListItem>()
    override fun loadFriends() {
        constrains.clear()
        IMDatabase.instance.deleteAlluser()
        doAsync {
            try{
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.sortBy { it[0] }
                usernames.forEachIndexed { index, s ->
                    var showFirstChar = index==0||s[0]!=usernames[index-1][0]
                    val constrain = FriendListItem(s,s[0].toString().uppercase(),showFirstChar)
                    constrains.add(constrain)

                    val friend=Friend(mutableMapOf("name" to s))
                    IMDatabase.instance.saveUser(friend)
                }
                uiThread {view.loadSuccess()}
            }catch (e:HyphenateException){
                uiThread {view.loadFailed()}
            }
        }
    }
}