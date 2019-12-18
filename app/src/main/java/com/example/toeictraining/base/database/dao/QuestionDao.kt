package com.example.toeictraining.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.toeictraining.base.entity.Question

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question")
    fun getAll(): List<Question>

    @Query("SELECT * FROM question WHERE id IN (:questionIds)")
    fun loadAllByIds(questionIds: IntArray): List<Question>

    @Query("SELECT correctAnswer FROM question WHERE id IN (:questionIds)")
    fun getCorrectAnswersByIds(questionIds: IntArray): List<String>

    @Insert
    fun insertAll(vararg questions: Question)

    @Delete
    fun delete(question: Question)
}