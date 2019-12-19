package com.example.toeictraining.ui.fragments.intro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toeictraining.R

class IntroScoreFragment : Fragment() {

    companion object {
        fun newInstance() = IntroScoreFragment()
    }

    private lateinit var viewModel: IntroScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_score_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(IntroScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
