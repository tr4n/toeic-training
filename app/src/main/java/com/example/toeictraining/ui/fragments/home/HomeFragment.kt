package com.example.toeictraining.ui.fragments.home


import androidx.fragment.app.Fragment
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment private constructor() : BaseFragment<HomeViewModel>() {

    override val layoutResource: Int get() = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    override fun initComponents() {

    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
