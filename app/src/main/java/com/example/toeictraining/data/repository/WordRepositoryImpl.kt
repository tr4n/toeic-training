package com.example.toeictraining.data.repository

import com.example.toeictraining.data.db.AppDatabase
import com.example.toeictraining.data.db.entity.Word

class WordRepositoryImpl(private val appDatabase: AppDatabase) : WordRepository {

    override suspend fun getWords(): List<Word> = appDatabase.wordDao().getWords()
}
