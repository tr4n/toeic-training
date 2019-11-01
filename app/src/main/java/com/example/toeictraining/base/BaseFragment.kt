package com.example.toeictraining.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

abstract class BaseFragment<VM : BaseViewModel> : Fragment(), LifecycleOwner {

    protected abstract val layoutResource: Int
    protected abstract val viewModel: VM
    protected abstract val lifecycleOwner: LifecycleOwner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initData()
        observeData()
    }

    protected abstract fun initComponents()

    protected open fun initData() {
        viewModel.onCreate()
    }

    protected open fun observeData() {
        viewModel.message.observe(lifecycleOwner, Observer { data ->
            context?.run {
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
