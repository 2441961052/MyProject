package com.example.im.contract

/*
        MVP协议类
        MVP-P层
 */
interface SplashContract {

    /*
          业务逻辑：检查登录状态  -> 1:已经登录，跳转到主界面
                                 2：没有登录，延时2秒，跳转到登录界面
     */

    interface Presenter : BasePresenter {
        fun checkLoginStatus()  //检查登录状态
    }

    interface View{
        fun isLogin()   //已经登录的ui处理
        fun notLogin()  //没有登录的ui处理
    }
}