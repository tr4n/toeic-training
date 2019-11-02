package com.example.toeictraining.ui.fragments.study

import com.example.toeictraining.data.db.entity.Topic
import com.example.toeictraining.di.ScopeNames
import org.koin.core.qualifier.named
import org.koin.dsl.module

val studyModule = module {
    single(named(ScopeNames.EMPTY_TOPIC)) {
        Topic()
    }
}
