package com.example.toeictraining.ui.fragments.test.do_test

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import kotlinx.android.synthetic.main.item_recyclerview_question_normal.view.*

class ListQuestionAdapter(
    private var context: Context,
    private var resources: MutableList<QuestionStatus>
) : RecyclerView.Adapter<ListQuestionAdapter.ViewHolder>() {

    val indexMain = MutableLiveData<Int>()
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
            QuestionStatus.Status.MAIN -> R.layout.item_recyclerview_question_main
            QuestionStatus.Status.DONE -> R.layout.item_recyclerview_question_done
            else -> R.layout.item_recyclerview_question_normal
        }
    }

    override fun getItemCount(): Int {
        return resources.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = resources[position]
        if (question.status == QuestionStatus.Status.MAIN) {
            indexMain.postValue(position)
            recyclerView.apply {
                (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
                    position,
                    (Resources.getSystem().displayMetrics.widthPixels - (Resources.getSystem().displayMetrics.density * 60).toInt()) / 2
                )
            }
        }
        holder.textQuestion.text = question.index.toString()
        holder.itemView.setOnClickListener {
            indexMain.value?.let {
                resources[it].status =
                    QuestionStatus.Status.NOT_DONE // load status from db instead of
            }
            question.status = QuestionStatus.Status.MAIN
            notifyDataSetChanged()
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textQuestion: TextView = view.text_question
    }
}