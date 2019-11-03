package com.example.toeictraining.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String =
        SimpleDateFormat("dd/MM/yyyy").format(Date(System.currentTimeMillis()))
}
