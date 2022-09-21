package com.example.im.v.fragment

import com.example.im.R
import com.example.im.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.header.*

class MessageFragment :BaseFragment(){
    override fun getLayoutId(): Int = R.layout.fragment_message
    override fun init() {
        super.init()
        tv_title.text= getString(R.string.message)
    }


}