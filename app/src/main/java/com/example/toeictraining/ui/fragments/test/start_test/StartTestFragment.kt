package com.example.toeictraining.ui.fragments.test.start_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.toeictraining.R
import com.example.toeictraining.ui.fragments.test.Constant
import com.example.toeictraining.ui.fragments.test.do_test.DoTestFragment
import com.example.toeictraining.ui.fragments.test.history.HistoryTestFragment
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.main.MainActivity
import com.example.toeictraining.utils.DateUtils
import kotlinx.android.synthetic.main.start_test_fragment.*

class StartTestFragment : Fragment() {

    companion object {
        val TAG = StartTestFragment::class.java.name
    }

    private lateinit var viewModel: StartTestViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StartTestViewModel::class.java)

        initViews()
        configNavigationIcon()
    }

    private fun initViews() {
        (activity as MainActivity).apply {
            setTitle("")
            setRightButtonText(getString(R.string.history))
            setOnClickToolbarRightButton(View.OnClickListener {
                (activity as MainActivity).openFragment(
                    HistoryTestFragment(),
                    true
                )
            })
        }
        setTextContent()
        button_start.setOnClickListener {
            arguments?.let {
                (activity as MainActivity).openFragment(
                    DoTestFragment(
                        it.getInt(HomeTestFragment.PART_ID)
                    ),
                    false
                )
            }
        }
    }

    private fun setTextContent() {
        val partID: Int? = arguments?.getInt(HomeTestFragment.PART_ID)
        text_correct_answer.text =
            getString(R.string.part).plus(" ").plus(partID)
        if (partID == 8) {
            text_correct_answer.text = getString(R.string.test_full)
        }
        partID?.let {
            text_time.text =
                getString(R.string.time).plus(" ")
                    .plus(DateUtils.secondsToStringTime(Constant.TIMES_PART[it]))
        }
    }

    private fun configNavigationIcon() {
        val actionBar = (activity as MainActivity).supportActionBar
        val actionBarDrawerToggle = (activity as MainActivity).getDrawerToggle()
        actionBarDrawerToggle.isDrawerIndicatorEnabled = false
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white_24dp)
        actionBarDrawerToggle.setToolbarNavigationClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }
}
