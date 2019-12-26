package com.example.toeictraining.ui.fragments.settings

import androidx.fragment.app.Fragment
import com.example.toeictraining.R
import com.example.toeictraining.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<SettingViewModel>() {

    override val layoutResource: Int get() = R.layout.fragment_setting

    override val viewModel: SettingViewModel by viewModel()

    override fun initComponents() {

    }

}
