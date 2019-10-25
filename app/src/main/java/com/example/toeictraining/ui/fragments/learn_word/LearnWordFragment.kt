package com.example.toeictraining.ui.fragments.learn_word

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.toeictraining.R

class LearnWordFragment : Fragment() {

    companion object {
        val TAG = LearnWordFragment::class.java.name
    }

    private lateinit var viewModel: LearnWordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.learn_word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LearnWordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
