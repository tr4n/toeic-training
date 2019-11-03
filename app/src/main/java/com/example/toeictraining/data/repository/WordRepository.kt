package com.example.toeictraining.data.repository

import com.example.toeictraining.data.db.entity.Word

interface WordRepository {
    suspend fun getWords(): List<Word>

    suspend fun getWordsByTopic(topicId: Int): List<Word>

    suspend fun updateWord(word: Word)
}
