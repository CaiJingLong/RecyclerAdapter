package com.sxw365.recyclerhelper.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by cai on 2017/9/29.
 */

open class RVAdapter<Data>(protected val list: MutableList<Data>?) : RecyclerView.Adapter<RVHolder>() {

    private val types = SparseArray<Cell>()

    private var processor: TypeProcessor? = TypeProcessorWrapper()

    fun addType(type: Int, cell: Cell) {
        types.append(type, cell)
    }

    fun removeType(type: Int) {
        types.remove(type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val cell = types.get(viewType)
        val view = LayoutInflater.from(parent.context).inflate(cell.layoutId, parent, false)
        return RVHolder(cell, view)
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        holder.cell.bindData(holder, position)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return if (processor != null) {
            processor!!.type(position)
        } else super.getItemViewType(position)
    }

    fun setTypeProcessor(processor: TypeProcessor?) {
        if (processor == null) {
            this.processor = TypeProcessorWrapper()
        } else {
            this.processor = processor
        }
    }

    fun addData(data: Data, animated: Boolean = false) {
        list?.add(data)
        if (!animated || list == null) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(list.size)
        }
    }

    interface TypeProcessor {
        fun type(position: Int): Int
    }

    private inner class TypeProcessorWrapper : TypeProcessor {

        override fun type(position: Int): Int {
            val data = list!![position]
            return if (data is RecyclerData) {
                (data as RecyclerData).type
            } else 0
        }
    }

}