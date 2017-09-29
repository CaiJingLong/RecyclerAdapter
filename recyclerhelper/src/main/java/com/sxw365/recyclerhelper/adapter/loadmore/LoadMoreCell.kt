package com.sxw365.recyclerhelper.adapter.loadmore

import com.sxw365.recyclerhelper.adapter.Cell

/**
 * Created by cai on 2017/9/29.
 */
interface LoadMoreCell : Cell {

    fun onLoadStart()

    fun onLoadStop()

    fun onLoadSuccess()

    fun onLoadFail()

    fun onLoadReset()
}