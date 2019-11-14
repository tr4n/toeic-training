package com.example.toeictraining.ui.fragments.test.do_test

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.example.toeictraining.R
import com.example.toeictraining.ui.main.MainActivity
import kotlinx.android.synthetic.main.do_test_fragment.*
import kotlin.math.abs


class DoTestFragment : Fragment() {

    companion object {
        val TAG = DoTestFragment::class.java.name
        const val THRESHOLD_SPEED = 60
    }

    private lateinit var viewModel: DoTestViewModel

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
    }

    private fun initViews() {
        (activity as MainActivity).setTitle("56:00")
        (activity as MainActivity).setRightButtonText(getString(R.string.submit_test))

        configNavigationIcon()

        val mutableList = mutableListOf<QuestionStatusEntity>()
        mutableList.add(QuestionStatusEntity(1, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(2, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(3, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(4, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(5, QuestionStatusEntity.Status.DONE))
        mutableList.add(QuestionStatusEntity(6, QuestionStatusEntity.Status.MAIN))
        mutableList.add(QuestionStatusEntity(7, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(8, QuestionStatusEntity.Status.DONE))
        mutableList.add(QuestionStatusEntity(9, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(10, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(11, QuestionStatusEntity.Status.DONE))
        mutableList.add(QuestionStatusEntity(12, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(13, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(14, QuestionStatusEntity.Status.NORMAL))
        mutableList.add(QuestionStatusEntity(15, QuestionStatusEntity.Status.NORMAL))
        for (i in 16..100) {
            mutableList.add(QuestionStatusEntity(i, QuestionStatusEntity.Status.NORMAL))
        }
        recyclerViewQuestion.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply {
                    scrollToPositionWithOffset(
                        0,
                        (Resources.getSystem().displayMetrics.widthPixels - (Resources.getSystem().displayMetrics.density * 60).toInt()) / 2
                    )
                }
            adapter = ListQuestionAdapter(context!!, mutableList)
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
                                } else if (speed < THRESHOLD_SPEED && lp2 == (mutableList.size - 1)) {
                                    setMainPosition(mutableList.size - 1)
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
                    mutableList[(adapter as ListQuestionAdapter).indexMain].status =
                        QuestionStatusEntity.Status.NORMAL // load lại status
                    mutableList[pos].status = QuestionStatusEntity.Status.MAIN
                }
            })
        }
    }

    private fun configNavigationIcon() {
        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarDrawerToggle = (activity as MainActivity).getDrawerToggle()
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBar?.setHomeAsUpIndicator(R.drawable.back_white_24dp)
        actionBarDrawerToggle.setToolbarNavigationClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}
