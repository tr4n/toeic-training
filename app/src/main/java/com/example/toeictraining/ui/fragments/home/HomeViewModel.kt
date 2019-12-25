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
import com.example.toeictraining.utils.PracticeMode
import kotlinx.coroutines.launch

class HomeViewModel(
    private val topicRepository: TopicRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private val parts = listOf("Part 1", "Part 2", "Part 3", "Part 4", "Part 5", "Part 6", "Part 7")
    private val _dailyWorks = MutableLiveData<List<DailyWork>>()
    val dailyWorks: LiveData<List<DailyWork>> get() = _dailyWorks
    override fun onCreate() {
        super.onCreate()
        getTopics()
    }

    private fun getDailyWorks(topics: List<Topic>, practiceMode: Int) {
        val dailyWorks = mutableListOf<String>()
        val numberOfWorks = 2 + practiceMode
        val vocabularyWorks = topics.shuffled().take(numberOfWorks - practiceMode).map {
            "Học từ vựng topic ${it.name}"
        }
        dailyWorks.addAll(parts.shuffled().take(practiceMode).map { "Làm bài test với $it" })
        dailyWorks.addAll(vocabularyWorks)
        _dailyWorks.value = dailyWorks.map {
            DailyWork(it, false)
        }
    }

    private fun getTopics() {
        viewModelScope.launch {
            val topics = topicRepository.getTopics()
            val practiceMode =
                sharedPreferences.getInt(Constants.PREFERENCE_PRACTICE_MODE, PracticeMode.HIGH)
            getDailyWorks(topics, practiceMode)
        }
    }

}