package com.example.toeictraining.ui.fragments.test.history

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.toeictraining.R
import com.example.toeictraining.base.database.AppDatabase
import com.example.toeictraining.base.entity.Exam
import kotlinx.android.synthetic.main.history_test_fragment.*

class HistoryTestFragment : Fragment() {

    private lateinit var viewModel: HistoryTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.history_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryTestViewModel::class.java)

        initViews()
    }

    private fun initViews() {
//        val listExam = AppDatabase.getInstance(context!!).examDao().getAll()
//        recyclerViewHistory.adapter = HistoryAdapter(context!!, listExam)
        recyclerViewHistory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}
