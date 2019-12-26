package com.example.toeictraining.ui.fragments.intro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

import com.example.toeictraining.R
import com.example.toeictraining.ui.main.MainActivity
import kotlinx.android.synthetic.main.intro_score_fragment.*

class IntroScoreFragment : Fragment() {

    private lateinit var viewModel: IntroScoreViewModel

    var spinner: Spinner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_score_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroScoreViewModel::class.java)

        spinnerSelect()
        nextButtonTapped()
        backButtonTapped()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }


    private fun spinnerSelect() {
        val scores = resources.getStringArray(R.array.Scores)
        spinner = spnScore
        if (spinner != null) {
            val adapter = ArrayAdapter(
                context!!,
                R.layout.intensity_spinner_item, scores
            )
            adapter.setDropDownViewResource(R.layout.intensity_spinner_dropdown_item)
            spinner!!.adapter = adapter
        }
    }

    private fun nextButtonTapped() {
        btnScoreNext.setOnClickListener{
            val fragment = IntroIntensityFragment()
            (activity as MainActivity).goNext(fragment)
        }
    }

    private fun backButtonTapped() {
        btnScoreBack.setOnClickListener{
            val fragment = IntroDateFragment()
            (activity as MainActivity).goBack(fragment)
        }
    }

}
