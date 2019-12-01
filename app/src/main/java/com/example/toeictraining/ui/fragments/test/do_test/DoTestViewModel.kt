package com.example.toeictraining.ui.fragments.test.do_test

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.toeictraining.base.database.dao.QuestionDao
import com.example.toeictraining.base.entity.Question
import kotlinx.coroutines.*

class DoTestViewModel(
    val database: QuestionDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var questionsLiveData = MutableLiveData<List<Question>>()

    fun getQuestionsLiveData(): MutableLiveData<List<Question>> {
        return questionsLiveData
    }

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            questionsLiveData.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): List<Question>? {
        return withContext(Dispatchers.IO) {
            Log.d("DoTestViewModel", "database.getAll().size = ${database.getAll().size}")
            database.getAll()
        }
    }


}
