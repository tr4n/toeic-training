package com.example.toeictraining.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTime(): String =
        SimpleDateFormat("dd/MM/yyyy").format(Date(System.currentTimeMillis()))

    fun secondsToStringTime(seconds: Long): String {
        var temp = seconds
        var hour = 0L
        var minute = 0L
        var second = 0L

        if (temp >= 3600) {
            hour = temp / (3600)
            temp -= hour * 3600
        }
        if (temp >= 60) {
            minute = temp / 60
            second = temp - minute * 60
        } else {
            second = temp
        }
        var result = ""
        if (hour > 0) result += "0$hour:"

        if (minute > 0) {
            result += if (minute < 10) "0$minute:" else "$minute:"
        } else {
            result += "00:"
        }
        if (second > 0) {
            result += if (second < 10) "0$second" else "$second"
        } else {
            result += "00"
        }
        return result
    }
}


