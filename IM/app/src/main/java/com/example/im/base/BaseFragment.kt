package com.example.im.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


abstract class BaseFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(),null)
    }
      //由于内部只有一行代码，可以改为等于，上面也可以
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()


    open fun init() {
        //在此设计公共方法，子类也可复写实现自己的方法
    }

    abstract fun getLayoutId(): Int
}