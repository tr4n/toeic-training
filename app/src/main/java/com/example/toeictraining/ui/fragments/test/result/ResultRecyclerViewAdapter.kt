package com.example.toeictraining.ui.fragments.test.result

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import kotlinx.android.synthetic.main.item_result.view.*

class ResultRecyclerViewAdapter(
    var context: Context,
    var results: MutableList<Result>
) : RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder>() {

    companion object {
        val TAG: String = ResultRecyclerViewAdapter::class.java.name
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result, null, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.textIndexQuestion.text =
            context.getString(R.string.index_question).plus(result.indexQuestion)
        holder.textResult.text = context.getString(R.string.result).plus(result.result)
        when (result.myAnswer) {
            "a" -> {
                holder.textA.setBackgroundColor(Color.parseColor("#886A08"))
                holder.textA.setTextColor(Color.WHITE)
            }
            "b" -> {
                holder.textB.setBackgroundColor(Color.parseColor("#886A08"))
                holder.textB.setTextColor(Color.WHITE)
            }
            "c" -> {
                holder.textC.setBackgroundColor(Color.parseColor("#886A08"))
                holder.textC.setTextColor(Color.WHITE)
            }
            "d" -> {
                holder.textD.setBackgroundColor(Color.parseColor("#886A08"))
                holder.textD.setTextColor(Color.WHITE)
            }
        }
        when (result.correctAnswer) {
            "a" -> {
                holder.textA.setBackgroundColor(Color.GREEN)
                holder.textA.setTextColor(Color.WHITE)
            }
            "b" -> {
                holder.textB.setBackgroundColor(Color.GREEN)
                holder.textB.setTextColor(Color.WHITE)
            }
            "c" -> {
                holder.textC.setBackgroundColor(Color.GREEN)
                holder.textC.setTextColor(Color.WHITE)
            }
            "d" -> {
                holder.textD.setBackgroundColor(Color.GREEN)
                holder.textD.setTextColor(Color.WHITE)
            }
        }

//        holder.setIsRecyclable(false)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textIndexQuestion: TextView = view.text_index_question
        val textResult: TextView = view.text_result
        val textA: TextView = view.text_a
        val textB: TextView = view.text_b
        val textC: TextView = view.text_c
        val textD: TextView = view.text_d
    }
}