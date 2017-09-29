package com.sxw365.recyclerhelper.adapter

import android.support.annotation.LayoutRes

/**
 * Created by cai on 2017/9/29.
 */

interface Cell {

    @get:LayoutRes
    val layoutId: Int

    fun bindData(holder: RVHolder, position: Int)
}
