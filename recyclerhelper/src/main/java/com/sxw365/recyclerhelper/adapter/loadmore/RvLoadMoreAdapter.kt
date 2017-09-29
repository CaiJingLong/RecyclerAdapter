package com.sxw365.recyclerhelper.adapter.loadmore

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sxw365.recyclerhelper.adapter.RVAdapter
import com.sxw365.recyclerhelper.log.logd

/**
 * Created by cai on 2017/9/29.
 */
class RvLoadMoreAdapter<Data>(list: MutableList<Data>?) : RVAdapter<Data>(list) {

    private var recyclerView: RecyclerView? = null

    var isLoading = false

    companion object {
        private val TAG = "RvLoadMoreAdapter";
    }

    var loadMoreCell: LoadMoreCell = LoadMoreCellWrapper()

    init {
        addType(1001, loadMoreCell)
        registerAdapterDataObserver(LoadMoreAdapter())
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
        if (recyclerView?.layoutManager != null) {
//            recyclerView.layoutManager
            recyclerView.addOnScrollListener(LoadMoreScrollListener())
        }
    }

    inner class LoadMoreAdapter : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
        }
    }

    fun onLoadMore() {
        logd("load more")
        if (isLoading) {
            return
        }
        loadMoreCell.onLoadStart()
        isLoading = true
    }

    fun onNoScrollToBottom() {
        val linearLayoutManager = recyclerView?.layoutManager as? LinearLayoutManager
        val view = (linearLayoutManager?.findViewByPosition(itemCount) as? LoadMoreCell).let {
            it?.onLoadReset()
        }

        logd("no scroll to bottom")
    }
}