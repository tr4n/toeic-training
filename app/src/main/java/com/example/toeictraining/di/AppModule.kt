package com.example.toeictraining.di

import com.example.toeictraining.base.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single(named(ScopeNames.APP_DATABASE)) {
        AppDatabase.getInstance(androidContext())
    }
}