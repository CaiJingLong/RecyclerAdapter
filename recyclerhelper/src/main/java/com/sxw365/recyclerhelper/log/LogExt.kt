package com.sxw365.recyclerhelper.log

import android.util.Log

/**
 * Created by cai on 2017/9/29.
 */
var LOG_DEBUG = true

fun Any.logd(msg: Any) {
    if (LOG_DEBUG) {
        Log.d(this.javaClass.simpleName, msg.toString())
    }
}