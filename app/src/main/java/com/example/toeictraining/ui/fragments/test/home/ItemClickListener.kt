package com.example.toeictraining.ui.fragments.test.home

import android.view.View

interface ItemClickListener {
    fun onClick(view: View, position: Int, isLongClick: Boolean)
}