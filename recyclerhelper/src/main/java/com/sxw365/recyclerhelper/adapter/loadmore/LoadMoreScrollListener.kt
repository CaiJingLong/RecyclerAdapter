package com.sxw365.recyclerhelper.adapter.loadmore

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by cai on 2017/9/29.
 */
class LoadMoreScrollListener : RecyclerView.OnScrollListener() {

    companion object {
        private val TAG = "LoadMoreScrollListener"
    }


    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val adapter = recyclerView?.adapter
        if (recyclerView != null && recyclerView.layoutManager is LinearLayoutManager && adapter is RvLoadMoreAdapter<*>) {
            val itemCount = adapter.itemCount
            val visiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            when {
                visiblePosition >= itemCount - 1 -> {
                    adapter.onLoadMore()
                }
                else -> adapter.onNoScrollToBottom()
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView != null && recyclerView.layoutManager is LinearLayoutManager) {
//                recyclerView.layoutManager.
            val visiblePosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
            Log.d(TAG, "visiblePosition = $visiblePosition")
        }
    }


}