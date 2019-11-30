package com.example.toeictraining.ui.fragments.reminder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseRecyclerAdapter
import com.example.toeictraining.base.BaseViewHolder
import com.example.toeictraining.base.entity.Topic
import kotlinx.android.synthetic.main.item_topic_review.view.*

class ReviewAdapter(
    callback: ReviewDiffUtilCallback
) : BaseRecyclerAdapter<Topic, ReviewAdapter.ReviewViewHolder>(callback) {

    override fun getItemLayoutResource(viewType: Int): Int = R.layout.item_topic_review

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewViewHolder(
        itemView = getItemView(parent, viewType)
    )

    class ReviewViewHolder(itemView: View) : BaseViewHolder<Topic>(itemView) {

        override fun onBindData(topic: Topic) {
            super.onBindData(topic)
            itemView.checkedTextTopicReview.text = topic.name
        }
    }

    class ReviewDiffUtilCallback : DiffUtil.ItemCallback<Topic>() {
        override fun areItemsTheSame(oldItem: Topic, newItem: Topic): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: Topic, newItem: Topic): Boolean =
            oldItem == newItem
    }
}