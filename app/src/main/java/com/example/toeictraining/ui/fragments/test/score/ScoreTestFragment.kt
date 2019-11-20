package com.example.toeictraining.ui.fragments.test.score

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toeictraining.R
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.main.MainActivity

class ScoreTestFragment : Fragment() {

    private lateinit var viewModel: ScoreTestViewModel

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
    }

    private fun initViews() {
        configNavigationIcon()
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
