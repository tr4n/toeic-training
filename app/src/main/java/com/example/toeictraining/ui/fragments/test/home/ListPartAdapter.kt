package com.example.toeictraining.ui.fragments.test.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import kotlinx.android.synthetic.main.navigation_view_header.view.*

class ListPartAdapter(
    private var context: Context,
    private var resources: Array<Int>,
    private var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ListPartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_recyclerview_image,
                null,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return resources.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(resources[position])
        holder.itemClickListener = itemClickListener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var itemClickListener: ItemClickListener
        var imageView: ImageView = view.imageView

        init {
            imageView.setOnClickListener { itemClickListener.onClick(view, adapterPosition, false) }
        }
    }
}