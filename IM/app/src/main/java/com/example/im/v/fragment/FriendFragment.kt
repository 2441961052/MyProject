package com.example.im.v.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im.R
import com.example.im.adapter.EMFriendItemAdapter
import com.example.im.adapter.FriendListAdapter
import com.example.im.base.BaseFragment
import com.example.im.contract.FriendContract
import com.example.im.presenter_M.FriendPresenter
import com.example.im.v.activity.AddFriendActivity
import com.hyphenate.chat.EMClient
import kotlinx.android.synthetic.main.fragment_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class FriendFragment : BaseFragment(), FriendContract.View {
    override fun getLayoutId(): Int = R.layout.fragment_friend
    val presenter = FriendPresenter(this)
    val ContractListener = object : EMFriendItemAdapter() {
        override fun onContactDeleted(username: String?) {
            presenter.loadFriends()
        }

        override fun onContactAdded(username: String?) {
            presenter.loadFriends()
        }
    }

    override fun init() {
        tv_title.text = getString(R.string.friend)
        iv_add.visibility = View.VISIBLE
        iv_add.setOnClickListener { requireActivity().startActivity<AddFriendActivity>() }
        SwipeRefreshLayout.apply {
            setColorSchemeResources(R.color.blue)
            isRefreshing = true
            setOnRefreshListener { presenter.loadFriends() }
        }
        RecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = FriendListAdapter(context, presenter.constrains)
        }
        EMClient.getInstance().contactManager().setContactListener(ContractListener)
        presenter.loadFriends()
    }

    override fun loadSuccess() {
        SwipeRefreshLayout.isRefreshing = false
        RecyclerView.adapter!!.notifyDataSetChanged()
        context!!.toast(R.string.loadSuccess)
    }

    override fun loadFailed() {
        SwipeRefreshLayout.isRefreshing = false
        context!!.toast(R.string.loadFailed)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().contactManager().removeContactListener(ContractListener)
    }
}