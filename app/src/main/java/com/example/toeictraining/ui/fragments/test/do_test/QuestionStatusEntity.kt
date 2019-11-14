package com.example.toeictraining.ui.fragments.test.do_test

import com.example.toeictraining.base.entity.QuestionEntity

class QuestionStatusEntity(
    var index: Int,
    var status: Status
) {
    enum class Status {
        NORMAL, DONE, MAIN
    }
}