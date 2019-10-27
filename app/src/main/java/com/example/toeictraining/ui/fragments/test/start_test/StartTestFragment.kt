package com.example.toeictraining.ui.fragments.test.start_test

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toeictraining.R

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
    }

}
