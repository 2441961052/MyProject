package com.example.im.contract
//注册界面的MVP协议
interface RegisterContract {
    interface Presenter : BasePresenter{
        fun register(name:String,password:String,checkPassword:String)
    }

    interface View{
        fun userNameError()
        fun passwordError()
        fun passwordDontMacth()
        fun onStartRegister()
        fun registerSuccess()
        fun registerFail()
        fun userExit()
    }
}