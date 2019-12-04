package com.example.toeictraining.ui.fragments.test.result

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toeictraining.R
import com.example.toeictraining.base.database.AppDatabase
import com.example.toeictraining.base.entity.Exam
import kotlinx.android.synthetic.main.result_test_fragment.*

class ResultTestFragment(
    private val idExam: Long
) : Fragment() {
    private lateinit var viewModel: ResultTestViewModel
    private val list = mutableListOf<Result>()
    private var exam: Exam? = null
    private var totalScore = 0

    companion object {
        val TAG = ResultTestFragment::class.java.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application: Application = requireNotNull(activity).application
        val examDao = AppDatabase.getInstance(application).examDao()
        val questionDao = AppDatabase.getInstance(application).questionDao()
        val viewModelFactory = ResultTestViewModelFactory(examDao, questionDao, application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ResultTestViewModel::class.java)
        return inflater.inflate(R.layout.result_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        handleObservable()
    }

    private fun handleObservable() {
        viewModel.getExamLiveData().observe(viewLifecycleOwner, Observer {
            exam = it
            viewModel.getQuestionsByIds(it.questionIdList)
        })

        viewModel.getQuestionsByIdsLiveData().observe(viewLifecycleOwner, Observer { questions ->
            exam?.answerList?.let { answerList ->
                for (i in questions.indices) {
                    list.add(Result(questions[i], answerList[i]))
                    if (questions[i].correctAnswer == answerList[i]) {
                        totalScore += 5
                    }
                }
                rv_result.adapter?.notifyDataSetChanged()
                exam?.time?.let { time ->
                    total_time.text = context?.getString(R.string.test_time_total).plus(time)
                }
                total_score.text = totalScore.toString()
            }
        })
    }

    private fun initViews() {
        viewModel.getExam(idExam)
        rv_result.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_result.adapter = ResultRecyclerViewAdapter(context!!, list)
    }

}
