package com.example.im.contract

//P层
interface LoginContract {
    //连接M层
    interface presenter : BasePresenter {
        fun login(userName:String,password:String)

    }
    //连接V层
    interface View{
        fun userNameError()
        fun passwordError()
        fun onStartLogin()
        fun loginSuccess()
        fun loginFail()
        fun applyPermission()
    }

}