package com.example.toeictraining.ui.fragments.test.history

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toeictraining.R
import com.example.toeictraining.base.database.AppDatabase
import kotlinx.android.synthetic.main.history_test_fragment.*

class HistoryTestFragment : Fragment() {

    companion object {
        val TAG: String = HistoryTestFragment::class.java.name
    }

    private lateinit var viewModel: HistoryTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application: Application = requireNotNull(this.activity).application
        val examDao = AppDatabase.getInstance(application).examDao()
        val viewModelFactory = HistoryTestViewModelFactory(examDao, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HistoryTestViewModel::class.java)
        return inflater.inflate(R.layout.history_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HistoryTestViewModel::class.java)

        initViews()
        handleObservable()
    }

    private fun handleObservable() {
        viewModel.getQuestionsLiveData().observe(viewLifecycleOwner, Observer {
            recyclerViewHistory.adapter = HistoryAdapter(context!!, it)
            recyclerViewHistory.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        })
    }

    private fun initViews() {

    }

}
