package com.example.toeictraining.ui.fragments.intro

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toeictraining.R

class IntroDateFragment : Fragment() {

    companion object {
        fun newInstance() = IntroDateFragment()
    }

    private lateinit var dateViewModel: IntroDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.intro_date_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dateViewModel = ViewModelProviders.of(this).get(IntroDateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
