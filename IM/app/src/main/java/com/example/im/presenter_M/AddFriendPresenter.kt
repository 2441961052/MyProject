package com.example.im.presenter_M

import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.im.contract.AddFriendContract
import com.example.im.data.AddFriendListItem
import com.example.im.data.db.IMDatabase
import com.hyphenate.chat.EMClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AddFriendPresenter(val view: AddFriendContract.View) : AddFriendContract.Presenter {
    val addFriendItems = mutableListOf<AddFriendListItem>()
    override fun search(key:String) {
        val query = BmobQuery<BmobUser>()
        query.addWhereContains("username",key)
            .addWhereNotEqualTo("username",EMClient.getInstance().currentUser)
        query.findObjects(object :FindListener<BmobUser>(){
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {
                if(p1==null) {
                    val allUser = IMDatabase.instance.getAllUser()
                    doAsync {
                        p0?.forEach {
                            var isadded=false
                            for(friend in allUser){
                                if(friend.name==it.username){
                                    isadded=true
                                }
                            }
                            val addFriendItem = AddFriendListItem(it.username,it.createdAt,isadded)
                            addFriendItems.add(addFriendItem)
                        }
                        uiThread { view.searchSuccess() }
                    }
                }
                else view.searchFailed()
            }

        })
    }
}