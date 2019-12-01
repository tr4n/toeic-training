package com.example.toeictraining.ui.fragments.test.result

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.toeictraining.R
import kotlinx.android.synthetic.main.result_test_fragment.*

class ResultTestFragment : Fragment() {

    private lateinit var viewModel: ResultTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResultTestViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        val list = mutableListOf<Result>()
        for (i in 1..200) {
            list.add(Result(i, "Sai", "a", "b"))
        }
        rv_result.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_result.adapter = ResultRecyclerViewAdapter(context!!, list)
    }

}
