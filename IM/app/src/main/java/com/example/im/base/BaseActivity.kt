package com.example.im.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle

import android.view.inputmethod.InputMethodManager

import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity(){
    val progressDialog by lazy { ProgressDialog(this) }
    val inputMethodManagerr by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        init()
    }

    open fun init() {
        //在此设计公共方法，子类也可复写实现自己的方法
    }

    abstract fun getLayoutResId(): Int

    fun showProgressDialog(msg:String){

        progressDialog.setMessage(msg)
        progressDialog.show()
    }
    fun dismissProgressDialog(){
        progressDialog.dismiss()
    }

    fun hideKeyBord(){
        inputMethodManagerr.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }

}