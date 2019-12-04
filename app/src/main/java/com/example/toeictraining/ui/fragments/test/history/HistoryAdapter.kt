package com.example.toeictraining.ui.fragments.test.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import com.example.toeictraining.base.database.AppDatabase
import com.example.toeictraining.base.entity.Exam
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(
    var context: Context,
    var exams: List<Exam>
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    companion object {
        val TAG = HistoryAdapter::class.java.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, null, false))
    }

    override fun getItemCount(): Int {
        return exams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exam = exams[position]
        holder.textIdExam.text = context.getString(R.string.id_exam).plus(exam.id.toString())
        holder.textPart.text = context.getString(R.string.part).plus(exam.part)
        holder.textTotalTime.text =
            context.getString(R.string.time).plus(DateUtils.secondsToStringTime(exam.time))
        val db = AppDatabase.getInstance(context)
        var score = 0
        for (i in 0..exam.answerList.size) {
            val correctAnswer =
                db.questionDao().loadAllByIds(intArrayOf(exam.questionIdList[i]))[0].correctAnswer
            if (exam.answerList[i] == correctAnswer) {
                score += 5
            }
        }
        Log.d(TAG, " totalscore = $score")
        holder.textTotalScore.text = context.getString(R.string.total_score).plus(score)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textIdExam: TextView = view.text_index_question
        var textTotalTime: TextView = view.text_result
        var textPart: TextView = view.text_correct_answer
        var textTotalScore: TextView = view.text_my_answer
    }
}