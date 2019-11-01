package com.example.toeictraining.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    abstract fun getItemLayoutResource(viewType: Int): Int

    override fun onBindViewHolder(holder: VH, position: Int) = holder.onBindData(items[position])

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun insertData(data: List<T>) {
        val oldPosition = itemCount
        items.addAll(data)
        val newPosition = itemCount
        if (newPosition > oldPosition) notifyItemRangeInserted(oldPosition, newPosition - 1)
    }

    protected fun getItemView(parent: ViewGroup, viewType: Int): View =
        LayoutInflater.from(parent.context)
            .inflate(getItemLayoutResource(viewType), parent, false)

    protected fun getItemData(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null
}
