package com.example.toeictraining.ui.fragments.test.do_test

import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeictraining.R
import com.example.toeictraining.base.entity.Question
import com.example.toeictraining.base.enums.QuestionLevel
import com.example.toeictraining.ui.fragments.test.score.ScoreTestFragment
import com.example.toeictraining.ui.main.MainActivity
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.do_test_fragment.*
import kotlinx.android.synthetic.main.score_test_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.math.abs
import kotlin.math.roundToLong


class DoTestFragment(val secondTotalTime: Long) : Fragment() {

    companion object {
        val TAG = DoTestFragment::class.java.name
        const val THRESHOLD_SPEED = 60
    }

    private lateinit var viewModel: DoTestViewModel
    lateinit var mediaPlayer: MediaPlayer
    val questions = mutableListOf<QuestionStatus>()
    var totalTime = 0L

    private var countDownTimer = object : CountDownTimer(secondTotalTime * 1000, 1000L) {
        override fun onFinish() {
            (activity as MainActivity).openFragment(
                R.id.content,
                ScoreTestFragment(questions, secondTotalTime),
                false
            )
        }

        override fun onTick(millisUntilFinished: Long) {
            totalTime = (millisUntilFinished / 1000.0).roundToLong()
            (activity as MainActivity).setTitle(
                DateUtils.secondsToStringTime(
                    (millisUntilFinished / 1000.0).roundToLong()
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.do_test_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DoTestViewModel::class.java)

        initViews()
        countDownTimer.start()
        handleObservable()
        listener()


//        button_play_sound.setOnClickListener {
//            mediaPlayer = MediaPlayer.create(context, R.raw.q1_p1).apply {
//                start()
//            }
//            it.background =
//                resources.getDrawable(R.drawable.pause_24, null)
//
//            mediaPlayer.setOnCompletionListener { mp ->
//                observer?.stop()
//                progressBar_sound.progress = mp.currentPosition
//                mediaPlayer.stop()
//                mediaPlayer.reset()
//            }
//            mediaPlayer.setOnBufferingUpdateListener { _, _ ->
//                Log.d(" UpdateListener", " vào")
//                progressBar_sound.progress =
//                    (mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration.toDouble() * 100).toInt()
//            }
////        mediaPlayer.setOnPreparedListener { btn_play_stop.setEnabled(true) }
//            observer = MediaObserver()
//            Thread(observer).start()
//        }
    }

    private fun listener() {
        (activity as MainActivity).toolbar_button_right.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
                .setView(R.layout.dialog_do_test)
                .setCancelable(false)
                .create()
            alertDialog?.let {
                it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.show()
                it.button_submit.setOnClickListener {

                    (activity as MainActivity).openFragment(
                        R.id.content,
                        ScoreTestFragment(questions, secondTotalTime - totalTime),
                        false
                    )
                    alertDialog.cancel()
                }
                it.button_not_submit.setOnClickListener {
                    alertDialog.cancel()
                }
            }
        }

        text_a.setOnClickListener {
            recyclerViewQuestion.adapter?.let { adapter ->
                (adapter as ListQuestionAdapter).indexMain.value?.let {
                    if (text_a.tag == null || text_a.tag == false) {
                        if (questions[it].answer == "b") removeAnswer(text_b)
                        if (questions[it].answer == "c") removeAnswer(text_c)
                        if (questions[it].answer == "d") removeAnswer(text_d)
                        questions[it].answer = "a"
                        setAnswer(text_a)
                    } else {
                        questions[it].answer = ""
                        removeAnswer(text_a)
                    }
                }
            }
        }

        text_b.setOnClickListener {
            recyclerViewQuestion.adapter?.let { adapter ->
                (adapter as ListQuestionAdapter).indexMain.value?.let {
                    if (text_b.tag == null || text_b.tag == false) {
                        if (questions[it].answer == "a") removeAnswer(text_a)
                        if (questions[it].answer == "c") removeAnswer(text_c)
                        if (questions[it].answer == "d") removeAnswer(text_d)
                        questions[it].answer = "b"
                        setAnswer(text_b)
                    } else {
                        questions[it].answer = ""
                        removeAnswer(text_b)
                    }
                }
            }
        }

        text_c.setOnClickListener {
            recyclerViewQuestion.adapter?.let { adapter ->
                (adapter as ListQuestionAdapter).indexMain.value?.let {
                    if (text_c.tag == null || text_c.tag == false) {
                        if (questions[it].answer == "a") removeAnswer(text_a)
                        if (questions[it].answer == "b") removeAnswer(text_b)
                        if (questions[it].answer == "d") removeAnswer(text_d)
                        questions[it].answer = "c"
                        setAnswer(text_c)
                    } else {
                        questions[it].answer = ""
                        removeAnswer(text_c)
                    }
                }
            }
        }

        text_d.setOnClickListener {
            recyclerViewQuestion.adapter?.let { adapter ->
                (adapter as ListQuestionAdapter).indexMain.value?.let {
                    if (text_d.tag == null || text_d.tag == false) {
                        if (questions[it].answer == "a") removeAnswer(text_a)
                        if (questions[it].answer == "b") removeAnswer(text_b)
                        if (questions[it].answer == "c") removeAnswer(text_c)
                        questions[it].answer = "d"
                        setAnswer(text_d)
                    } else {
                        questions[it].answer = ""
                        removeAnswer(text_d)
                    }
                }
            }
        }
    }

    private fun setAnswer(answerView: TextView) {
        answerView.tag = true
        answerView.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorPrimary,
                null
            )
        )
        answerView.setTextColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.white,
                null
            )
        )
    }

    private fun removeAnswer(answerView: TextView) {
        answerView.tag = false
        answerView.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.white,
                null
            )
        )
        answerView.setTextColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.black,
                null
            )
        )
    }

    private fun handleObservable() {
        (recyclerViewQuestion.adapter as ListQuestionAdapter).indexMain.observe(
            viewLifecycleOwner,
            Observer {
                scrollView2.scrollTo(0, 0)
                val question = questions[it]
                text_question_content.visibility = View.VISIBLE
                text_question_content.text = question.data.content
                text_a.text = question.data.a
                text_b.text = question.data.b
                text_c.text = question.data.c
                text_d.text = question.data.d
                removeAnswer(text_a)
                removeAnswer(text_b)
                removeAnswer(text_c)
                removeAnswer(text_d)
                if (question.answer == "a") setAnswer(text_a)
                if (question.answer == "b") setAnswer(text_b)
                if (question.answer == "c") setAnswer(text_c)
                if (question.answer == "d") setAnswer(text_d)
            })
    }

//    private var observer: MediaObserver? = null
//
//    private inner class MediaObserver : Runnable {
//        private val stop = AtomicBoolean(false)
//        fun stop() {
//            stop.set(true)
//        }
//
//        override fun run() {
//            while (!stop.get()) {
//                progressBar_sound.progress =
//                    (mediaPlayer.currentPosition.toDouble() / mediaPlayer.duration.toDouble() * 100).toInt()
//                try {
//                    Thread.sleep(200)
//                } catch (ex: Exception) {
//                    Log.e(TAG, ex.toString())
//                }
//            }
//        }
//    }

    private fun initViews() {
        (activity as MainActivity).setRightButtonText(getString(R.string.submit_test))
        configNavigationIcon()

        //fake data
        for (i in 1..200) {
            questions.add(
                QuestionStatus(
                    i, QuestionStatus.Status.NOT_DONE, Question(
                        i,
                        "Halls will come to Life with Music\n" +
                                " \n" +
                                "Albert Hall and Royal Hall have disclosed their schedules for the upcoming season, with everything (1)........ classical music to stand up comedy acts and lots in between.\n" +
                                "\n" +
                                "The Smiths are set to make their debut at 100-year-old Albert hall on Jan 25, 2007. The venerable venue (2)........ will host Bruce\n" +
                                " \n" +
                                "Thornton on January 17, Ron Ghanem on January 3, Terry Lightfoot on January 9, and folk superstar Judith Bachman on February 12, 13 and 14.\n" +
                                "\n" +
                                "At Royal Hall, in its classical (3)........, American soprano Rosemary Voigt, Canadian soprano Jonathon Pierre,\n" +
                                "Canadian baritone Mel Finley, and Polish contralto George Plodles (4)........  on the bill.",
                        "A. Series",
                        "B. Steps",
                        "C. Occasions",
                        "D. Separation",
                        null,
                        QuestionLevel.MEDIUM,
                        "a",
                        1
                    ),
                    ""
                )
            )
        }

        questions[6].data.content = "Dreamaker\n" +
                "The professionals that make your nights comfortable\n" +
                "\n" +
                "The Dreamaker Plus (9)........ the exclusive coil system everyone’s been talking about.\n" +
                "\n" +
                "For the past 5- years, the experts at Dreamaker (10)........ their time and effort to bring the Americans a good night’s rest by using our reliable and proven technology.\n" +
                "\n" +
                "Compared to any conventional spring systems available in the industry, the Dreamaker Plus has nearly twice the coils of any others. Quality comfort layers and fabrics (11)........ to ensure a comfortable and durable sleeping surface.\n" +
                "\n" +
                "By increasing the wire thickness in the outer two rows, Dreamaker Plus (12)........ a firmer seating edge, increases the usable sleeping space, and helps to prevent that “roll out of bed” feeling."

        questions[5].status = QuestionStatus.Status.MAIN
        questions[1].data.soundLink = "123123133"

        setRecyclerQuestion()
    }

    private fun setRecyclerQuestion() {
        recyclerViewQuestion.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                    scrollToPositionWithOffset(
                        0,
                        (Resources.getSystem().displayMetrics.widthPixels - (Resources.getSystem().displayMetrics.density * 60).toInt()) / 2
                    )
                }
            adapter = ListQuestionAdapter(context, questions)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL
                ).apply {
                    setDrawable(context?.getDrawable(R.drawable.divider_recyclerview_horizontal_9)!!)
                }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                private var firstTimePoint = 0L
                private var lastTimePoint = 0L
                private var fp1 = 0

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            lastTimePoint = System.currentTimeMillis()
                            val fp2 =
                                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            val lp2 =
                                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            if ((fp2 - fp1) != 0) {
                                val speed = abs((lastTimePoint - firstTimePoint) / (fp2 - fp1))
                                if (speed < THRESHOLD_SPEED && fp2 == 0) {
                                    setMainPosition(0)
                                } else if (speed < THRESHOLD_SPEED && lp2 == (questions.size - 1)) {
                                    setMainPosition(questions.size - 1)
                                } else {
                                    val item =
                                        (layoutManager as LinearLayoutManager).findViewByPosition(
                                            abs(lp2 - fp2) / 2 + fp2
                                        )
                                    val textQuestion =
                                        (item as LinearLayout).getChildAt(0) as TextView
                                    val positionQuestion =
                                        textQuestion.text.toString().toInt() - 1 // question bắt đầu = 1, position bắt đầu = 0
                                    setMainPosition(positionQuestion)
                                }
                                adapter?.notifyDataSetChanged()
                            }
                        }

                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                            firstTimePoint = System.currentTimeMillis()
                            fp1 =
                                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        }
                    }
                }

                private fun setMainPosition(pos: Int) {
                    (adapter as ListQuestionAdapter).indexMain.value?.let {
                        questions[it].status =
                            QuestionStatus.Status.NOT_DONE // load lại status
                    }
                    questions[pos].status = QuestionStatus.Status.MAIN
                }
            })
        }
    }

    private fun configNavigationIcon() {
        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarDrawerToggle = (activity as MainActivity).getDrawerToggle()
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white_24dp)
        actionBarDrawerToggle.setToolbarNavigationClickListener {
            val alertDialog = AlertDialog.Builder(context)
                .setView(R.layout.dialog_do_test)
                .setCancelable(false)
                .create()
            alertDialog?.let {
                it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.show()
                it.button_submit.text = "Không"
                it.button_not_submit.text = "Thoát"
                it.button_submit.setOnClickListener {
                    alertDialog.cancel()
                }
                it.button_not_submit.setOnClickListener {
                    (activity as MainActivity).onBackPressed()
                    countDownTimer.cancel()
                    alertDialog.cancel()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}
