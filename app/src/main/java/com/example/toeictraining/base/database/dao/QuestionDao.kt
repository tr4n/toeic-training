package com.example.toeictraining.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.toeictraining.base.entity.Question2

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question")
    fun getAll(): List<Question2>

    @Query("SELECT * FROM question WHERE id IN (:questionIds)")
    fun loadAllByIds(questionIds: IntArray): List<Question2>

    @Insert
    fun insertAll(vararg questions: Question2)

    @Delete
    fun delete(question: Question2)
}