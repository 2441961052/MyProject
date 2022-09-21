package com.example.im.contract

interface FriendContract {
    interface presenter : BasePresenter{
        fun loadFriends()
    }
    interface View{
        fun loadSuccess()
        fun loadFailed()
    }
}