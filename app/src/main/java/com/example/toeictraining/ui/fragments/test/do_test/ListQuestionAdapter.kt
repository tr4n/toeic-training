package com.example.toeictraining.ui.fragments.test.do_test

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import kotlinx.android.synthetic.main.item_recyclerview_question_normal.view.*

class ListQuestionAdapter(
    private var context: Context,
    private var resources: MutableList<QuestionStatusEntity>
) : RecyclerView.Adapter<ListQuestionAdapter.ViewHolder>() {

    var indexMain = 0
    private lateinit var recyclerView: RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(viewType, null, false))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemViewType(position: Int): Int {
        return when (resources[position].status) {
            QuestionStatusEntity.Status.MAIN -> R.layout.item_recyclerview_question_main
            QuestionStatusEntity.Status.DONE -> R.layout.item_recyclerview_question_done
            else -> R.layout.item_recyclerview_question_normal
        }
    }

    override fun getItemCount(): Int {
        return resources.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (resources[position].status == QuestionStatusEntity.Status.MAIN) {
            indexMain = position
            recyclerView.apply {
                (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    position,
                    (Resources.getSystem().displayMetrics.widthPixels - (Resources.getSystem().displayMetrics.density * 60).toInt()) / 2
                )
            }
        }
        holder.textQuestion.text = resources[position].index.toString()
        holder.itemView.setOnClickListener {
            resources[indexMain].status =
                QuestionStatusEntity.Status.NORMAL // load status from db instead of
            resources[position].status = QuestionStatusEntity.Status.MAIN
            notifyDataSetChanged()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textQuestion: TextView = view.text_question
    }
}