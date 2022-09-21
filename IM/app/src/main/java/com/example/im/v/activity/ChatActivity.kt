package com.example.im.v.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.im.R
import com.example.im.adapter.EMMessageListenerAdapter
import com.example.im.adapter.MsgListAdapter
import com.example.im.base.BaseActivity
import com.example.im.contract.ChatContract
import com.example.im.presenter_M.ChatPresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class ChatActivity : BaseActivity(),ChatContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_chat
    val presenter = ChatPresenter(this)
    val MessageListener = object : EMMessageListenerAdapter(){
        override fun onMessageReceived(messages: MutableList<EMMessage>?) {
            presenter.addMessage(username,messages)
            runOnUiThread {
                rc_info.adapter!!.notifyDataSetChanged()
                scrollToBottom()
            }

        }
    }
    lateinit var username:String
    override fun init() {
        super.init()
        initHeadet()
        initEdit()
        EMClient.getInstance().chatManager().addMessageListener(MessageListener)
        initRecycleView()
        btn_send.setOnClickListener { send() }
        presenter.loadMsg(username)
    }

    private fun initRecycleView() {
        rc_info.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter=MsgListAdapter(context,presenter.msglist)
            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if(newState==RecyclerView.SCROLL_STATE_IDLE){
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if(linearLayoutManager.findFirstVisibleItemPosition()==0){
                            presenter.loadMoreMsg(username)
                        }
                    }
                }
            })
        }
    }

    private fun send() {

        var msg = ed_text.text.trim().toString()
        hideKeyBord()
        presenter.sendMsg(username,msg)
        scrollToBottom()
    }

    private fun scrollToBottom() {
        rc_info.scrollToPosition(presenter.msglist.size-1)
    }

    private fun initEdit() {
        ed_text.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                btn_send.isEnabled =!s.isNullOrEmpty()
            }

        })
    }

    private fun initHeadet() {
        iv_back.visibility=View.VISIBLE
        iv_back.setOnClickListener { finish() }

        username = intent.getStringExtra("username").toString()
        val titleString = String.format(getString(R.string.chatWith),username)
        tv_title.text = titleString
    }

    override fun startSendMsg() {
        rc_info.adapter!!.notifyDataSetChanged()
    }

    override fun sendMsgSuccess() {
        rc_info.adapter!!.notifyDataSetChanged()
        toast(R.string.sendMsgSuccess)
        ed_text.text.clear()
    }

    override fun sendMsgFailed() {
        toast(R.string.sendMsgFailed)
    }

    override fun msgLoaded() {
        rc_info.adapter!!.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun moreMsgLoad(size: Int) {
        rc_info.adapter!!.notifyDataSetChanged()
        rc_info.scrollToPosition(size)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(MessageListener)
    }
}