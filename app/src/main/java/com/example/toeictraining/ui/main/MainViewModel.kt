package com.example.toeictraining.ui.main

import android.content.SharedPreferences
import android.text.format.DateUtils
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.utils.Constants

class MainViewModel(private val sharedPreferences: SharedPreferences) : BaseViewModel() {

    override fun onCreate() {
        super.onCreate()
        val lastAccessTime = sharedPreferences.getLong(Constants.PREFERENCE_LAST_ACCESS, 0L)
        if (lastAccessTime == 0L || !DateUtils.isToday(lastAccessTime)) {
            resetDailyWork()
        }
    }

    private fun resetDailyWork() {
        sharedPreferences.edit().run {
            putBoolean(Constants.PREFERENCE_DAILY_WORK_1, false)
            putBoolean(Constants.PREFERENCE_DAILY_WORK_2, false)
            putBoolean(Constants.PREFERENCE_DAILY_WORK_3, false)
            putBoolean(Constants.PREFERENCE_DAILY_WORK_4, false)
            putBoolean(Constants.PREFERENCE_DAILY_WORK_5, false)
            putBoolean(Constants.PREFERENCE_DAILY_WORK_6, false)
            putLong(Constants.PREFERENCE_LAST_ACCESS, System.currentTimeMillis())
        }.apply()
    }
}
