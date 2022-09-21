package com.example.im.adapter

import com.hyphenate.EMContactListener

open class EMFriendItemAdapter : EMContactListener {
    override fun onContactAdded(username: String?) {
    }

    override fun onContactDeleted(username: String?) {
    }

    override fun onContactInvited(username: String?, reason: String?) {
    }

    override fun onFriendRequestAccepted(username: String?) {
    }

    override fun onFriendRequestDeclined(username: String?) {
    }
}