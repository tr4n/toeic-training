package com.example.toeictraining.ui.fragments.vocabulary

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.data.model.Category
import com.example.toeictraining.data.repository.CategoryRepository

private const val TAG = "VocabularyViewModel"

class VocabularyViewModel(
    private val categoryRepository: CategoryRepository
) : BaseViewModel() {

    val categories: LiveData<List<Category>> = liveData {
        try {
            emit(categoryRepository.getCategories())
        } catch (e: Exception) {
            emit(emptyList())
            e.printStackTrace()
        }
    }
}
