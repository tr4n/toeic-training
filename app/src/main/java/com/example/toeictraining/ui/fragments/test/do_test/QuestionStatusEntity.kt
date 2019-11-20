package com.example.toeictraining.ui.fragments.test.do_test

class QuestionStatusEntity(
    var index: Int,
    var status: Status
) {
    enum class Status {
        NORMAL, DONE, MAIN
    }
}