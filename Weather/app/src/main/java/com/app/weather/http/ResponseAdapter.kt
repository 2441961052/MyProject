package com.app.network.http

data class ResponseAdapter<T>(val result:T?,var status:Boolean,var error:String)