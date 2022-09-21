package com.example.im.v.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.AddFriendListAdapter
import com.example.im.base.BaseActivity
import com.example.im.contract.AddFriendContract
import com.example.im.presenter_M.AddFriendPresenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

class AddFriendActivity : BaseActivity(),AddFriendContract.View{
    override fun getLayoutResId(): Int = R.layout.activity_add_friend
    val presenter = AddFriendPresenter(this)
    override fun init() {
        tv_title.text = getString(R.string.addFriend)
        search.setOnClickListener {

            searchFriend()
            //EMClient.getInstance().contactManager().addContact(name, "reason");
        }
        RecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendListAdapter(context,presenter.addFriendItems)
        }
    }

    private fun searchFriend() {
        hideKeyBord()
        showProgressDialog(getString(R.string.searching))
        val name=et_username.text.toString().trim()
        presenter.search(name)
    }

    override fun searchSuccess() {
        dismissProgressDialog()
        toast(R.string.searchSuccess)
        RecyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun searchFailed() {
        dismissProgressDialog()
        toast(R.string.searchFailed)
    }

}