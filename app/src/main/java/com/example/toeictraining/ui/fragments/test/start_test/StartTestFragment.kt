package com.example.toeictraining.ui.fragments.test.start_test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.toeictraining.R

import com.example.toeictraining.ui.fragments.test.do_test.DoTestFragment

import com.example.toeictraining.ui.fragments.test.home.TestFragment
import com.example.toeictraining.ui.main.MainActivity
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
    }

    private fun initViews() {
        (activity as MainActivity).apply {
            setTitle("")
            setRightButtonText(getString(R.string.history))
            setOnClickToolbarRightButton(View.OnClickListener {
                Toast.makeText(
                    context,
                    "Vào",
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
        setTextContent()
        configNavigationIcon()
        button_start.setOnClickListener {
            (activity as MainActivity).openFragment(R.id.content, DoTestFragment(), true)
        }
    }

    private fun setTextContent() {
        val partID = arguments?.getInt(TestFragment.PART_ID)
        if (partID == 8) {
            text_part.text = getString(R.string.test_full)
        } else {
            text_part.text =
                getString(R.string.part).plus(" ").plus(partID)
        }
        text_time.text =
            getString(R.string.time).plus(" ").plus(arguments?.getString(TestFragment.TIME))
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
