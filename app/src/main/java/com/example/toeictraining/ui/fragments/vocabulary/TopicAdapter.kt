package com.example.toeictraining.ui.fragments.vocabulary

import android.view.View
import android.view.ViewGroup
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseRecyclerAdapter
import com.example.toeictraining.base.BaseViewHolder
import com.example.toeictraining.data.db.entity.Topic
import com.example.toeictraining.utils.Constants
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicAdapter : BaseRecyclerAdapter<Topic, TopicAdapter.TopicViewHolder>() {

    var onItemClick: (Topic) -> Unit = {}

    override fun getItemLayoutResource(viewType: Int): Int = R.layout.item_topic

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopicViewHolder(
        itemView = getItemView(parent, viewType),
        onItemClick = onItemClick
    )

    class TopicViewHolder(
        itemView: View,
        private val onItemClick: (Topic) -> Unit
    ) : BaseViewHolder<Topic>(itemView) {

        override fun onBindData(topic: Topic) = with(itemView) {
            super.onBindData(topic)

            progressTopic.apply {
                max = topic.total ?: 12
                progress = topic.master ?: 0
                secondaryProgress = max - progress
            }

            textNameTopic?.text = topic.name
            textLastTime?.text = topic.lastTime ?: Constants.DEFAULT_LAST_TIME
        }

        override fun onItemClickListener(itemData: Topic) = onItemClick(itemData)
    }
}
