package com.example.takeout.model.utils

import java.util.*

/**
 * Created by lidongzhi on 2016/10/20.
 */
class OrderObservable private constructor(): Observable() {


    companion object{
        val instance = OrderObservable()

        /* 订单状态
         * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单*/
         val ORDERTYPE_UNPAYMENT = "10"
         val ORDERTYPE_SUBMIT = "20"
         val ORDERTYPE_RECEIVEORDER = "30"
         val ORDERTYPE_DISTRIBUTION = "40"

        // 骑手状态：接单、取餐、送餐
         val ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE = "43"
         val ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL = "46"
         val ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL = "48"
         val ORDERTYPE_SERVED = "50"
         val ORDERTYPE_CANCELLEDORDER = "60"
    }

    fun newMsgComing(data: String) {
        setChanged()
        notifyObservers(data);
    }
}