package com.example.toeictraining.ui.fragments.vocabulary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.data.model.Category
import com.example.toeictraining.data.repository.TopicRepository

class VocabularyViewModel(private val topicRepository: TopicRepository) : BaseViewModel() {

    val categories: LiveData<List<Category>> = liveData {
        try {
            emit(topicRepository.getCategories())
            Log.d("VocabularyViewModel", "run here")
        } catch (e: Exception) {
            emit(emptyList())
            message.value = e.toString()
            e.printStackTrace()
        }
    }
}
