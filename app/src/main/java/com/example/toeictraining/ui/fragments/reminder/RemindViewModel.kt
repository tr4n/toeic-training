package com.example.toeictraining.ui.fragments.reminder

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.base.entity.Topic
import com.example.toeictraining.data.repository.TopicRepository
import com.example.toeictraining.utils.Constants
import kotlinx.coroutines.launch

class RemindViewModel(
    private val topicRepository: TopicRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    val remindTime: LiveData<String?> = liveData {
        try {
            emit(sharedPreferences.getString(Constants.PREFERENCE_TIME_REMINDER, null))
        } catch (e: Exception) {
            emit(null)
            message.value = e.toString()
            e.printStackTrace()
        }
    }

    val topics: LiveData<List<Topic>> = liveData {
        try {
            emit(topicRepository.getTopics())
        } catch (e: Exception) {
            emit(emptyList())
            message.value = e.toString()
            e.printStackTrace()
        }
    }

    fun updateTopicReviews(topic: Topic) {
        try {
            viewModelScope.launch {
                topicRepository.updateTopic(topic)
            }
            message.value = "Enable topic ${topic.name}"
        } catch (e: Exception) {
            message.value = e.message
        }
    }

    fun saveRemindTime(time: String) {
        sharedPreferences.edit().run {
            putString(Constants.PREFERENCE_TIME_REMINDER, time)
            commit()
        }
    }
}
