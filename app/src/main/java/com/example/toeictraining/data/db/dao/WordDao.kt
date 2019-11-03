package com.example.toeictraining.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.toeictraining.data.db.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM ${Word.TABLE_NAME}")
    suspend fun getWords(): List<Word>

    @Query("SELECT * FROM ${Word.TABLE_NAME} WHERE ${Word.FIELD_TOPIC_ID} = :topicId")
    suspend fun getWordsByTopic(topicId: Int): List<Word>

    @Update
    suspend fun updateWord(word: Word)
}
