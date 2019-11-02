package com.example.toeictraining.ui.fragments.study

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.toeictraining.base.BaseViewModel
import com.example.toeictraining.data.db.entity.Topic
import com.example.toeictraining.data.db.entity.Word
import com.example.toeictraining.data.repository.WordRepository
import com.example.toeictraining.di.ScopeNames
import kotlinx.coroutines.launch
import org.koin.core.get
import org.koin.core.qualifier.named

class StudyViewModel(
    private val wordRepository: WordRepository
) : BaseViewModel() {

    var mainTopic: Topic = get(named(ScopeNames.EMPTY_TOPIC))

    private val words = mutableListOf<Word>()
    private var wordPosition: Int = 0

    private val _word: MutableLiveData<Word> = MutableLiveData()

    val word: LiveData<Word> get() = _word

    override fun onCreate() {
        viewModelScope.launch {
            words.addAll(wordRepository.getWordsByTopic(mainTopic.id))
            _word.value = words[wordPosition % words.size]
        }
    }
}
