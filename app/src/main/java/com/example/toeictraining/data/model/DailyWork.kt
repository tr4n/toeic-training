package com.example.toeictraining.data.model

data class DailyWork(val content: String, var isDone: Boolean, private val type: Int) {

    fun isTest() = type == TEST_WORK

    fun isVocabulary() = type == VOCABULARY_WORK

    companion object {
        const val TEST_WORK = 0
        const val VOCABULARY_WORK = 1
    }
}
