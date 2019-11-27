package com.example.toeictraining.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.toeictraining.base.entity.Exam
import com.example.toeictraining.base.entity.Question

@Dao
interface ExamDao {
    @Query("SELECT * FROM exam")
    fun getAll(): List<Exam>

    @Query("SELECT * FROM exam WHERE id IN (:examIds)")
    fun loadAllByIds(examIds: IntArray): List<Exam>

    @Insert
    fun insertAll(vararg exam: Exam)

    @Delete
    fun delete(exam: Exam)
}