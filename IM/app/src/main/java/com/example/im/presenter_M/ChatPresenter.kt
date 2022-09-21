package com.example.im.presenter_M

import com.example.im.adapter.EMCallBackAdater
import com.example.im.contract.ChatContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import org.jetbrains.anko.doAsync

class ChatPresenter(val view: ChatContract.View) : ChatContract.Presenter {
    val msglist = mutableListOf<EMMessage>()
    override fun sendMsg(context: String, msg: String) {
        val emMessage = EMMessage.createTxtSendMessage(msg,context)
        emMessage.setMessageStatusCallback(object :EMCallBackAdater(){
            override fun onSuccess() {
                uiThread { view.sendMsgSuccess() }
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.sendMsgFailed() }
            }
        })
        msglist.add(emMessage)
        view.startSendMsg()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addMessage(username: String, messages: MutableList<EMMessage>?) {
        messages?.let { msglist.addAll(it) }
        //更新消息为已读
        //获取联系人信息
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()
    }

    override fun loadMsg(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            msglist.addAll(conversation.allMessages)
            uiThread { view.msgLoaded() }
        }

    }

    override fun loadMoreMsg(username: String) {
        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            var startId = msglist[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startId, 10)
            msglist.addAll(0,loadMoreMsgFromDB)
            uiThread { view.moreMsgLoad(loadMoreMsgFromDB.size) }
        }
    }
}