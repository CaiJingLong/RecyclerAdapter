package com.sxw365.recyclerhelper.adapter

import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by cai on 2017/9/29.
 */

class RVHolder(val cell: Cell, view: View) : RecyclerView.ViewHolder(view) {

    private val views = SparseArray<View>()

    fun <V : View> getView(@IdRes id: Int): V {
        var view: View? = views.get(id)
        if (view == null) {
            view = itemView.findViewById(id)
            views.append(id, view)
        }
        return view as V
    }

    fun getTextView(@IdRes id: Int): TextView {
        return getView(id)
    }

    fun getImageView(@IdRes id: Int): ImageView {
        return getView(id)
    }
}
