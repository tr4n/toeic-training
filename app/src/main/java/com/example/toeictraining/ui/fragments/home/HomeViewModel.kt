package com.example.toeictraining.ui.fragments.home

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.base.entity.Topic
import com.example.toeictraining.data.model.DailyWork
import com.example.toeictraining.data.repository.TopicRepository
import com.example.toeictraining.utils.Constants
import com.example.toeictraining.utils.DateUtil
import kotlinx.coroutines.launch

class HomeViewModel(
    private val topicRepository: TopicRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val _dailyWorks = MutableLiveData<List<DailyWork>>()
    private val _recentResults = MutableLiveData<List<Int>>()

    val dailyWorks: LiveData<List<DailyWork>> get() = _dailyWorks
    val recentResults: LiveData<List<Int>> get() = _recentResults

    override fun onCreate() {
        super.onCreate()
        getTopics()
        getRecentResults()
    }

    private fun getTopics() {
        viewModelScope.launch {
            val topics = topicRepository.getTopics()
            getDailyWorks(topics)
        }
    }

    private fun getDailyWorks(topics: List<Topic>) {
        val partsData = sharedPreferences.getString(Constants.PREFERENCE_DAILY_WORK_PART, null)
        val topicsData = sharedPreferences.getString(Constants.PREFERENCE_DAILY_WORK_TOPIC, null)
        if (partsData == null || topicsData == null) return

        val partIds = partsData.split(Constants.ARRAY_SEPARATOR).map { it.toInt() }
        val topicIds = topicsData.split(Constants.ARRAY_SEPARATOR).map { it.toInt() }

        _dailyWorks.value = mutableListOf<DailyWork>().apply {
            addAll(partIds.map {
                DailyWork(content = "Làm bài thi thử Part $it", isDone = false)
            })
            addAll(topicIds.map {
                DailyWork(
                    content = "Học từ vựng topic ${topics[it].name}",
                    isDone = topics[it].lastTime?.let { time ->
                        DateUtil.isToday(time)
                    } ?: false
                )
            })
        }
    }

    private fun getRecentResults() {
        val recentResultsData =
            sharedPreferences.getString(Constants.PREFERENCE_RECENT_RESULTS_PROGRESS, null)
        recentResultsData ?: return
        _recentResults.value = recentResultsData.split(Constants.ARRAY_SEPARATOR).map { it.toInt() }
    }
}
