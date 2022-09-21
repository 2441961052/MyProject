package com.example.im.v.activity

import com.example.im.R
import com.example.im.base.BaseActivity
import com.example.im.factory.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.header.*

class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int =R.layout.activity_main
    override fun init() {
        super.init()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,FragmentFactory.instance.getFragment(it.itemId))
            transaction.commit()
            true
        }
    }

}