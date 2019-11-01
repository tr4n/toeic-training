package com.example.toeictraining.di

import com.example.toeictraining.data.repository.CategoryRepositoryImpl
import com.example.toeictraining.data.repository.WordRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {

    single(named(ScopeNames.WORD_REPOSITORY)) {
        WordRepositoryImpl(appDatabase = get(named(ScopeNames.APP_DATABASE)))
    }

    single(named(ScopeNames.CATEGORY_REPOSITORY)) {
        CategoryRepositoryImpl(appDatabase = get(named(ScopeNames.APP_DATABASE)))
    }
}
