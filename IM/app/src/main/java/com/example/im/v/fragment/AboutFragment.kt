package com.example.im.v.fragment

import com.example.im.R
import com.example.im.adapter.EMCallBackAdater
import com.example.im.base.BaseFragment
import com.example.im.v.activity.LoginActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AboutFragment:BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_about
    override fun init() {
        tv_title.text= getString(R.string.about)
        val username = EMClient.getInstance().currentUser.toString()
        btn_exit.text = "退（"+username+")出"
        btn_exit.setOnClickListener {logout()}
    }

    private fun logout() {
        EMClient.getInstance().logout(true,object:EMCallBackAdater(){
            override fun onSuccess() {
                context?.runOnUiThread {
                    toast(R.string.exitSuccess)
                    context?.startActivity<LoginActivity>()
                    activity?.finish()
                }
            }

            override fun onError(code: Int, error: String?) {
                context?.runOnUiThread { toast(R.string.exitFail) }
            }
        })
    }
}