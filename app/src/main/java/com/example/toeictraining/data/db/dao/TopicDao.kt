package com.example.toeictraining.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.toeictraining.data.db.entity.Topic

@Dao
interface TopicDao {
    @Query("SELECT * FROM ${Topic.TABLE_NAME}")
    suspend fun getTopics(): List<Topic>

    @Query("SELECT DISTINCT category FROM ${Topic.TABLE_NAME}")
    suspend fun getCategoryNames(): List<String>
}
