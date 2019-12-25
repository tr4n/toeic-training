package com.example.toeictraining.ui.fragments.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseRecyclerAdapter
import com.example.toeictraining.base.BaseViewHolder
import com.example.toeictraining.data.model.DailyWork
import kotlinx.android.synthetic.main.item_topic_review.view.*

class DailyWorkAdapter(
    callback: DailyWorkDiffUtilCallback
) : BaseRecyclerAdapter<DailyWork, DailyWorkAdapter.DailyWorkViewHolder>(callback) {

    override fun getItemLayoutResource(viewType: Int): Int = R.layout.item_topic_review

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWorkViewHolder =
        DailyWorkViewHolder(getItemView(parent, viewType))

    override fun onBindViewHolder(holder: DailyWorkViewHolder, position: Int) {
        holder.onBindData(position, getItem(position))
    }

    class DailyWorkViewHolder(itemView: View) : BaseViewHolder<DailyWork>(itemView) {

        override fun onBindData(itemPosition: Int, itemData: DailyWork) {
            super.onBindData(itemPosition, itemData)
            itemView.checkedTextTopicReview.apply {
                text = itemData.content
                isChecked = itemData.isDone == true
            }
        }
    }

    class DailyWorkDiffUtilCallback : DiffUtil.ItemCallback<DailyWork>() {
        override fun areItemsTheSame(oldItem: DailyWork, newItem: DailyWork): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: DailyWork, newItem: DailyWork): Boolean =
            oldItem == newItem
    }
}
