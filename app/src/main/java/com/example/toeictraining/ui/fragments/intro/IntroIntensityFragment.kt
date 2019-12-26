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
import com.example.toeictraining.ui.fragments.test.home.HomeTestFragment
import com.example.toeictraining.ui.main.MainActivity
import kotlinx.android.synthetic.main.intro_intensity_fragment.*
import kotlinx.android.synthetic.main.intro_score_fragment.*

class IntroIntensityFragment : Fragment() {


    private lateinit var viewModel: IntroIntensityViewModel

    var spinner: Spinner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_intensity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroIntensityViewModel::class.java)

        spinnerSelect()
        finishButtonTapped()
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
        val intensity = resources.getStringArray(R.array.Intensity)
        spinner = spnIntensity
        if (spinner != null) {
            val adapter = ArrayAdapter(
                context!!,
                R.layout.intensity_spinner_item, intensity
            )
            adapter.setDropDownViewResource(R.layout.intensity_spinner_dropdown_item)
            spinner!!.adapter = adapter
        }
    }

    private fun finishButtonTapped() {
        btnIntensityFinish.setOnClickListener{
            val fragment = HomeTestFragment()
            //(activity as MainActivity).openFragment(R.id.content, fragment, true)
            (activity as MainActivity).goNext(fragment)
        }
    }

    private fun backButtonTapped() {
        btnIntensityBack.setOnClickListener{
            val fragment = IntroScoreFragment()
            (activity as MainActivity).goBack(fragment)
        }
    }

}
