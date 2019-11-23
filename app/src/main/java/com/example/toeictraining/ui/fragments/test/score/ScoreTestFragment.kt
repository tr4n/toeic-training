package com.example.toeictraining.ui.fragments.test.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.toeictraining.R
import com.example.toeictraining.ui.fragments.test.do_test.QuestionStatus
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.main.MainActivity
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.score_test_fragment.*

class ScoreTestFragment(val questions: List<QuestionStatus>, val totalTime: Long) : Fragment() {

    companion object {
        val TAG = ScoreTestFragment::class.java.name
    }

    private lateinit var viewModel: ScoreTestViewModel
    var totalScore = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.score_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScoreTestViewModel::class.java)

        initViews()

        button_continue_do_test.setOnClickListener {
            (activity as MainActivity).openFragment(R.id.content, HomeTestFragment(), false)
        }

        //caculate score
        for (question in questions) {
            if (question.answer.equals(question.data.correctAnswer)) {
                totalScore += 5
            }
        }
        total_score.text = totalScore.toString()
        text_total_time.text =
            getString(R.string.test_time_total).plus(DateUtils.secondsToStringTime(totalTime))
        var listenScore = 0
        var readScore = 0

        for (question in questions) {
            if (question.data.correctAnswer.equals(question.answer)) {
                if (question.data.soundLink != null) {
                    listenScore += 5
                    continue
                }
                readScore += 5
            }
        }
        text_listen_score.text = "Điểm nghe: $listenScore"
        text_read_score.text = "Điểm đọc: $readScore"
        configNavigationIcon()
    }

    private fun initViews() {
        (activity as MainActivity).setRightButtonText("")
        (activity as MainActivity).setTitle("")
    }

    private fun configNavigationIcon() {
        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarDrawerToggle = (activity as MainActivity).getDrawerToggle()
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
        actionBarDrawerToggle.setToolbarNavigationClickListener {
            (activity as MainActivity).openFragment(R.id.content, HomeTestFragment(), false)
        }
    }
}
