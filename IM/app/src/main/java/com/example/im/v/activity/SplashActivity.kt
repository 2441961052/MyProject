package com.example.im.v.activity
//加载界面---MVP->V层
import android.os.Handler
import com.example.im.R
import com.example.im.base.BaseActivity
import com.example.im.contract.SplashContract
import com.example.im.presenter_M.SplashPresenter
import org.jetbrains.anko.startActivity


class SplashActivity : BaseActivity(),SplashContract.View{
    override fun getLayoutResId(): Int {
        return R.layout.activity_splash
    }

    private val handler by lazy { Handler() }

    private val presenter = SplashPresenter(this)

    override fun init() {
        super.init()
        presenter.checkLoginStatus()
    }

    override fun isLogin() {
        startActivity<MainActivity>()
        finish()
    }

    override fun notLogin() {
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        },2000)
    }

}