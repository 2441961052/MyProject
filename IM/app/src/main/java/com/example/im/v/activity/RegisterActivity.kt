package com.example.im.v.activity
import cn.bmob.v3.Bmob
import com.example.im.R
import com.example.im.base.BaseActivity
import com.example.im.contract.RegisterContract
import com.example.im.presenter_M.RegisterPresenter
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegisterActivity : BaseActivity() ,RegisterContract.View{

    val presenter=RegisterPresenter(this)
    override fun init() {
        super.init()
        Bmob.initialize(this, "5127e5a6362f3a9a1e7537c90f957bd1")
        EMClient.getInstance().init(applicationContext, null);
        EMClient.getInstance().setDebugMode(true);
        bt_submit.setOnClickListener{register()}
        tv_toLogin.setOnClickListener { startActivity<LoginActivity>() }
    }

    private fun register() {
        hideKeyBord()
        val name = et_name.text.trim().toString()
        val password = et_password.text.trim().toString()
        val checkPassword = et_passwordCheck.text.trim().toString()
        presenter.register(name,password,checkPassword)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun userNameError() {
        et_name.error = getString(R.string.et_nameError)
    }

    override fun passwordError() {
        et_password.error = getString(R.string.et_passwordError)
    }

    override fun passwordDontMacth() {
        et_passwordCheck.error = getString(R.string.passwordNotMatch)
    }

    override fun onStartRegister() {
        showProgressDialog(getString(R.string.submitRigester))
    }

    override fun registerSuccess() {
        dismissProgressDialog()
        toast(R.string.registerSuccess)
        finish()
    }

    override fun registerFail() {
        dismissProgressDialog()
        toast(getString(R.string.registerFail))
    }

    override fun userExit() {
        dismissProgressDialog()
        toast(getString(R.string.userExit))
    }

}