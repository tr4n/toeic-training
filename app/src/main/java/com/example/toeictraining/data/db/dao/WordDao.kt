package com.example.toeictraining.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.toeictraining.data.db.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM ${Word.TABLE_NAME}")
    suspend fun getWords(): List<Word>
}