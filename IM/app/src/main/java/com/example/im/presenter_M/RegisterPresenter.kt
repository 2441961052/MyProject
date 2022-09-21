package com.example.im.presenter_M

import android.util.Log
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.example.im.contract.RegisterContract
import com.example.im.extentions.isValidPassword
import com.example.im.extentions.isValidUserName
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class RegisterPresenter(val view: RegisterContract.View):RegisterContract.Presenter {
    override fun register(name: String, password: String, checkPassword: String) {
        if (name.isValidUserName()){
            if(password.isValidPassword()){
                if(password.equals(checkPassword)){
                    view.onStartRegister()
                    //开始注册
                    registerBmob(name,password)
                }else view.passwordDontMacth()
            }else view.passwordError()
        }else view.userNameError()
    }

    private fun registerBmob(name: String, password: String) {
        val bu = BmobUser()
        bu.username = name
        bu.setPassword(password)
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(currentUser: BmobUser?, ex: BmobException?) {
                if (ex == null) {
                    registerEaseMob(name,password)
                } else {
                    if(ex.errorCode==202) view.userExit()
                    else view.registerFail()

                }
            }
        })
    }

    private fun registerEaseMob(name: String, password: String) {
        Log.i("TAG", "1")
        doAsync {
            try {
                Log.i("TAG", "2")
                EMClient.getInstance().createAccount(name, password)
                Log.i("TAG", "3")
                uiThread {
                    view.registerSuccess()
                    Log.i("TAG", "4")
                }
            } catch (e: HyphenateException) {
                Log.i("TAG", "5" + e.errorCode)
                uiThread {
                    view.registerFail()
                    Log.i("TAG", "6")
                }
            }
        }

    }

}