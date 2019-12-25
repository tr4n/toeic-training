package com.example.toeictraining.ui.fragments.intro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.toeictraining.R
import kotlinx.android.synthetic.main.intro_score_fragment.*
import kotlinx.android.synthetic.main.navigation_view_header.*

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
    }

    private fun spinnerSelect() {
        val scores = resources.getStringArray(R.array.Scores)
        spinner = spnIntensity
        if (spinner != null) {
            val adapter = ArrayAdapter(
                context!!,
                R.layout.intensity_spinner_item, scores
            )
            adapter.setDropDownViewResource(R.layout.intensity_spinner_dropdown_item)
            spinner!!.adapter = adapter
        }
    }
}
