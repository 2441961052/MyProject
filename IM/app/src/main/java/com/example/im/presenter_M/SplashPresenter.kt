package com.example.im.presenter_M

import com.example.im.contract.SplashContract
import com.hyphenate.chat.EMClient
/*
    MVP-M层
 */
class SplashPresenter(private val view: SplashContract.View) :SplashContract.Presenter {
    override fun checkLoginStatus() {
        if(isLogin()) view.isLogin() else view.notLogin()
    }


    private fun isLogin(): Boolean  =
        EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
}