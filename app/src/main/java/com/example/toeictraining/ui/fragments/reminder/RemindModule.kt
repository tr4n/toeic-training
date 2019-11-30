package com.example.toeictraining.ui.fragments.reminder

import org.koin.dsl.module

val remindModule = module {

    single {
        ReviewAdapter(ReviewAdapter.ReviewDiffUtilCallback())
    }
}