package com.example.toeictraining.ui.fragments.home


import android.graphics.Color
import androidx.fragment.app.Fragment
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment private constructor() : BaseFragment<HomeViewModel>() {

    override val layoutResource: Int get() = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    override fun initComponents() {
        showDaysLeft()
    }

    private fun showDaysLeft() {
        val startDay = "12/12/2019"
        val deadline = "02/02/2020"
        val today = "12/01/2020"
        //DateUtils.getCurrentTime()
        val daysLeft = DateUtils.getDaysBetween(today, deadline)
        val totalDays = DateUtils.getDaysBetween(startDay, deadline)
        val currentProgress = 1 - daysLeft.toFloat() / totalDays

        flipmeter.setValue(daysLeft.toInt(), false)
        textMsgDaysLeft.text = getString(R.string.msg_remind_days_left, today, daysLeft.toString())

        val color = when {
            currentProgress <= 0.5f -> COLOR_GREEN
            currentProgress <= 0.8f -> COLOR_YELLOW
            else -> COLOR_RED
        }
        seekBar.apply {
            max = totalDays.toFloat()
            currentValue = (totalDays - daysLeft).toFloat()
            setTextMin(startDay)
            setTextMax(deadline)
            setRegionColorLeft(Color.parseColor(color))
        }
//        textStartDay.text = startDay
//        textDeadline.text = deadline
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        private const val COLOR_GREEN = "#4CAF50"
        private const val COLOR_YELLOW = "#FFFFEB3B"
        private const val COLOR_RED = "#FFFF0000"
    }
}
