package com.example.im.v.activity


import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.im.R
import com.example.im.presenter_M.LoginPresenter
import com.example.im.base.BaseActivity
import com.example.im.contract.LoginContract
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() ,LoginContract.View{

    private val presenter = LoginPresenter(this)

    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }


    override fun init() {
        super.init()
        tv_toRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }
        bt_submit.setOnClickListener { login()}
    }

    private fun login(){
        hideKeyBord()
        if(hasWriteExternalStoragePermission()){
            val userName = et_name.text.trim().toString()
            val password = et_password.text.trim().toString()
            presenter.login(userName,password)
        }else applyExternalStoragePermission()
    }
    //申请权限
    private fun applyExternalStoragePermission() {
        val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this,permission,0)
    }

    //检查是否有写磁盘的权限
    private fun hasWriteExternalStoragePermission(): Boolean {
        val res = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return res==PackageManager.PERMISSION_GRANTED
    }
    //检查权限返回
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            login()
        }else{
            toast(R.string.getPermissionFail)
        }
    }

    override fun userNameError() {
        et_name.error = getString(R.string.et_nameError)
    }

    override fun passwordError() {
        et_password.error = getString(R.string.et_passwordError)
    }

    override fun onStartLogin() {
        showProgressDialog(getString(R.string.progressDialogSuccess))
    }

    override fun loginSuccess() {
        dismissProgressDialog()
        startActivity<MainActivity>()
        finish()
    }

    override fun loginFail() {
        dismissProgressDialog()
        toast(R.string.progressDialogFail)
    }

    override fun applyPermission() {
        TODO("Not yet implemented")
    }

}