package com.example.toeictraining.ui.fragments.test.score

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.toeictraining.R
import com.example.toeictraining.ui.fragments.test.do_test.QuestionStatus
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.fragments.test.result.ResultTestFragment
import com.example.toeictraining.ui.main.MainActivity
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.score_test_fragment.*
import kotlin.math.roundToInt

class ScoreTestFragment(
    val questions: List<QuestionStatus>,
    private val totalTime: Long,
    private val timestamp: Long,
    private val part: Int
) :
    Fragment(), View.OnClickListener {

    companion object {
        val TAG = ScoreTestFragment::class.java.name
    }

    val level = arrayOf("Yếu", "Trung Bình", "Giỏi")

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

        text_result.text =
            getString(R.string.test_time_total).plus(DateUtils.secondsToStringTime(totalTime))
        var listenScore = 0
        var readScore = 0

        val listAnswer = mutableListOf<String>()
        val listQuestionId = mutableListOf<Int>()

        for (question in questions) {
            if (question.data.correctAnswer == question.answer) {
                if (question.data.soundLink != null) {
                    listenScore += 5
                    continue
                }
                readScore += 5
            }
            //create exam
            listAnswer.add(question.answer)
            listQuestionId.add(question.data.id)
        }
        //save exam
//        val exam = Exam(
//            0,
//            listQuestionId,
//            listAnswer,
//            totalTime.toInt(),
//            part,
//            timestamp
//        )
//        AppDatabase.getInstance(context!!).examDao().insertAll(exam)
        //
        text_listen_score.text = getString(R.string.listen_score).plus("$listenScore")
        text_read_score.text = getString(R.string.read_score).plus("$readScore")
        totalScore = readScore + listenScore
        total_score.text = totalScore.toString()
        if (questions.size == 200) {
            text_expand?.visibility = View.VISIBLE
            text_expand?.setOnClickListener {
                if (it.tag == null) {
                    layout_detail.visibility = View.VISIBLE
                    text_expand.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.expand_48,
                        0
                    )

                    //part 1
                    setDetailPart(1, 6, progressbar_part_1, text_evaluate_1)
                    //part 2
                    setDetailPart(7, 31, progressbar_part_2, text_evaluate_2)
                    //part 3
                    setDetailPart(32, 70, progressbar_part_3, text_evaluate_4)
                    //part 4
                    setDetailPart(71, 100, progressbar_part_4, text_evaluate_4)
                    //part 5
                    setDetailPart(101, 130, progressbar_part_5, text_evaluate_5)
                    //part 6
                    setDetailPart(131, 146, progressbar_part_6, text_evaluate_6)
                    //part 7
                    setDetailPart(147, 200, progressbar_part_7, text_evaluate_7)

                    //set tag
                    it.tag = true
                } else {
                    layout_detail.visibility = View.GONE
                    text_expand.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.collapse_48,
                        0
                    )
                    it.tag = null
                }
            }
            button_learn_more_1?.setOnClickListener(this)
            button_learn_more_2?.setOnClickListener {
                Toast.makeText(context, "button 2", Toast.LENGTH_SHORT).show()
            }
            button_learn_more_3?.setOnClickListener {
                Toast.makeText(context, "button 3", Toast.LENGTH_SHORT).show()
            }
            button_learn_more_4?.setOnClickListener {
                Toast.makeText(context, "button 4", Toast.LENGTH_SHORT).show()
            }
            button_learn_more_5?.setOnClickListener {
                Toast.makeText(context, "button 5", Toast.LENGTH_SHORT).show()
            }
            button_learn_more_6?.setOnClickListener {
                Toast.makeText(context, "button 6", Toast.LENGTH_SHORT).show()
            }
            button_learn_more_7?.setOnClickListener {
                Toast.makeText(context, "button 7", Toast.LENGTH_SHORT).show()
            }
        }
        configNavigationIcon()
    }

    private fun setDetailPart(
        startQuestion: Int,
        endQuestion: Int,
        progressBar: ProgressBar,
        textView: TextView
    ) {
        // tính số câu đúng part 1
        var countCorrectAnswerPart = 0
        for (i in startQuestion.minus(1)..endQuestion.minus(1)) {
            if (questions[i].answer == questions[i].data.correctAnswer) {
                countCorrectAnswerPart++
            }
        }
        //set progress cho part 1
        val progress = (countCorrectAnswerPart * 100.0 / (endQuestion - startQuestion)).roundToInt()
        progressBar.progress = progress
        //set level of part
        if (progress < 40) {
            textView.text = level[0]
        }
        if (progress in 41..79) {
            textView.text = level[1]
        }
        if (80 < progress) {
            textView.text = level[2]
            button_learn_more_1.visibility = View.INVISIBLE
            button_learn_more_1.isEnabled = false
        }
    }

    private fun initViews() {
        (activity as MainActivity).setRightButtonText("")
        (activity as MainActivity).setTitle("")

        button_continue_do.setOnClickListener(this)
        button_result.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_continue_do -> {
                (activity as MainActivity).openFragment(R.id.content, HomeTestFragment(), false)
            }
            R.id.button_result -> {

                (activity as MainActivity).openFragment(R.id.content, ResultTestFragment(), true)
            }
            R.id.button_learn_more_1->{
                Toast.makeText(context, "button 1", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
