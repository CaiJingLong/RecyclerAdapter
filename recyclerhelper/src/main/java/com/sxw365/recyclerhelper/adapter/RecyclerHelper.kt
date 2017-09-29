package com.sxw365.recyclerhelper.adapter

import android.app.Application

import java.lang.ref.WeakReference

/**
 * Created by cai on 2017/9/29.
 */

object RecyclerHelper {

    private var application: WeakReference<Application>? = null

    fun init(application: Application) {
        RecyclerHelper.application = WeakReference(application)
    }

    fun getApplication(): Application? {
        return application?.get()
    }
}
