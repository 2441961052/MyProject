package com.example.im.contract

interface AddFriendContract : BasePresenter {
    interface Presenter{
        fun search(key:String)
    }
    interface View{
        fun searchSuccess()
        fun searchFailed()
    }
}