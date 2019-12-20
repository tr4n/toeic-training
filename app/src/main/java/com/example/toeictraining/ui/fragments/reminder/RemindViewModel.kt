package com.example.toeictraining.ui.fragments.reminder

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _reviewTopic: MutableLiveData<String> = MutableLiveData()
    val reviewTopic: LiveData<String> get() = _reviewTopic

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

    val reviewMode: LiveData<Boolean> = liveData {
        try {
            emit(sharedPreferences.getBoolean(Constants.PREFERENCE_TOPIC_REMINDER, false))
        } catch (e: Exception) {
            emit(false)
        }
    }

    fun updateTopicReviews(topic: Topic) {
        try {
            viewModelScope.launch {
                topicRepository.updateTopic(topic)
            }
            _reviewTopic.value = topic.name
      //      message.value = "Enable topic ${topic.name}"
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

    fun removeRemindTime() {
        sharedPreferences.edit().run {
            remove(Constants.PREFERENCE_TIME_REMINDER)
            commit()
        }
    }

    fun saveEnableReviewMode(isEnable: Boolean) {
        sharedPreferences.edit().run {
            putBoolean(Constants.PREFERENCE_TOPIC_REMINDER, isEnable)
            commit()
        }
    }
}
