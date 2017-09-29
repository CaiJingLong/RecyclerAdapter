package com.sxw365.recyclerhelper.refresh

import android.support.v4.widget.SwipeRefreshLayout
import android.view.ViewGroup

/**
 * Created by cai on 2017/9/29.
 */

object RefreshHelper {

    fun attachToSwipeRefreshLayout(viewGroup: ViewGroup, listener: SwipeRefreshLayout.OnRefreshListener): SwipeRefreshLayout {
        val parent = viewGroup.parent
        if (parent != null && parent is ViewGroup) {
            val layoutParams = viewGroup.layoutParams
            parent.removeView(viewGroup)
            val swipeRefreshLayout = SwipeRefreshLayout(viewGroup.context)
            parent.addView(swipeRefreshLayout, layoutParams)
            val params = ViewGroup.MarginLayoutParams(layoutParams)
            swipeRefreshLayout.addView(viewGroup, params)
            val delegate = RefreshDelegate(swipeRefreshLayout, listener)
            swipeRefreshLayout.setOnRefreshListener(delegate)
            return swipeRefreshLayout
        }

        TODO("the error need not happen ,please check your viewGroup have parent")
    }

    private class RefreshDelegate internal constructor(private val swipeRefreshLayout: SwipeRefreshLayout, private val listener: SwipeRefreshLayout.OnRefreshListener?) : SwipeRefreshLayout.OnRefreshListener {

        override fun onRefresh() {
            listener?.onRefresh()
        }
    }
}
