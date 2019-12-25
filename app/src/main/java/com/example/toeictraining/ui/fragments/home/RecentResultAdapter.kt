package com.example.toeictraining.ui.fragments.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseRecyclerAdapter
import com.example.toeictraining.base.BaseViewHolder
import com.example.toeictraining.utils.Constants
import kotlinx.android.synthetic.main.item_analysis_result_part.view.*

class RecentResultAdapter(
    callback: RecentResultDiffUtilCallback
) : BaseRecyclerAdapter<Int, RecentResultAdapter.RecentResultViewHolder>(callback) {

    var onItemClick: (Int) -> Unit = {}

    override fun getItemLayoutResource(viewType: Int): Int = R.layout.item_analysis_result_part

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentResultViewHolder =
        RecentResultViewHolder(getItemView(parent, viewType), onItemClick)

    override fun onBindViewHolder(holder: RecentResultViewHolder, position: Int) {
        holder.onBindData(position, getItem(position))
    }

    class RecentResultViewHolder(
        itemView: View,
        private val onItemClick: (Int) -> Unit
    ) : BaseViewHolder<Int>(itemView) {

        override fun onBindData(itemPosition: Int, itemData: Int) {
            super.onBindData(itemPosition, itemData)
            itemView.apply {
                textPartName.text =
                    context.resources.getString(R.string.title_part, itemPosition + 1)
                progressBar.apply {
                    max = 100
                    progress = itemData
                }
                textPartEvaluationResult.text = when (itemData) {
                    in 0..49 -> Constants.partResultEvaluations[0]
                    in 50..79 -> Constants.partResultEvaluations[1]
                    in 80..94 -> Constants.partResultEvaluations[2]
                    else -> Constants.partResultEvaluations[3]
                }.toString()
            }
        }

        override fun onItemClickListener(itemData: Int) = onItemClick(itemData)
    }

    class RecentResultDiffUtilCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
            oldItem == newItem
    }
}
