package com.example.toeictraining.di

import com.example.toeictraining.ui.fragments.home.HomeViewModel
import com.example.toeictraining.ui.fragments.reminder.RemindViewModel
import com.example.toeictraining.ui.fragments.study.StudyViewModel
import com.example.toeictraining.ui.fragments.vocabulary.VocabularyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        VocabularyViewModel(topicRepository = get(named(ScopeNames.TOPIC_REPOSITORY)))
    }

    viewModel {
        StudyViewModel(
            wordRepository = get(named(ScopeNames.WORD_REPOSITORY)),
            topicRepository = get(named(ScopeNames.TOPIC_REPOSITORY))
        )
    }

    viewModel {
        RemindViewModel(
            topicRepository = get(named(ScopeNames.TOPIC_REPOSITORY)),
            sharedPreferences = get(named(ScopeNames.SHARED_PREFERENCES))
        )
    }

    viewModel {
        HomeViewModel(

        )
    }
}
