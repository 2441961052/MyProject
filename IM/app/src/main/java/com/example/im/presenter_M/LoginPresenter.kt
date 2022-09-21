package com.example.im.presenter_M

import com.example.im.adapter.EMCallBackAdater
import com.example.im.contract.LoginContract
import com.example.im.extentions.isValidPassword
import com.example.im.extentions.isValidUserName
import com.hyphenate.chat.EMClient

class LoginPresenter(private val view: LoginContract.View) : LoginContract.presenter{
    override fun login(userName: String, password: String) {
        //校验用户名
        if(userName.isValidUserName()){
            //校验密码
            if (password.isValidPassword()){
                view.onStartLogin()
                //登录到环信
                loginEaseMob(userName,password)
            }else view.passwordError()
        }else view.userNameError()
    }


    private fun loginEaseMob(userName: String, password: String) {
        EMClient.getInstance().login(userName,password,object: EMCallBackAdater() {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                uiThread { view.loginSuccess() }
            }

            override fun onError(code: Int, error: String?) {
                uiThread { view.loginFail() }
            }

        })

    }
}