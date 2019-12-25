package com.example.toeictraining.ui.fragments.home


import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import com.example.toeictraining.data.model.DailyWork
import com.example.toeictraining.utils.DateUtil
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment private constructor() : BaseFragment<HomeViewModel>() {

    override val layoutResource: Int get() = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    private val dailyWorkAdapter = DailyWorkAdapter(DailyWorkAdapter.DailyWorkDiffUtilCallback())

    override fun initComponents() {
        recyclerDailyWorks.adapter = dailyWorkAdapter
        showDaysLeft()
    }

    private fun showDaysLeft() {
        val startDay = "12/12/2019"
        val deadline = "02/02/2020"
        val today = DateUtil.getCurrentTime()
        val daysLeft = DateUtil.getDaysBetween(today, deadline)
        val totalDays = DateUtil.getDaysBetween(startDay, deadline)
        val currentProgress = 1 - daysLeft.toFloat() / totalDays

        flipmeter.setValue(daysLeft.toInt(), false)
        textToday.text = context?.getString(R.string.title_today, today)
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
    }

    override fun observeData() = with(viewModel) {
        super.observeData()
        dailyWorks.observe(viewLifecycleOwner, Observer(::showDailyWorks))
    }

    private fun showDailyWorks(dailyWorks: List<DailyWork>) {
        dailyWorkAdapter.submitList(dailyWorks)
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        private const val COLOR_GREEN = "#4CAF50"
        private const val COLOR_YELLOW = "#FFFFEB3B"
        private const val COLOR_RED = "#FFFF0000"
    }
}
