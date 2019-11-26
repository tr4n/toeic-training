package com.example.toeictraining.base.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Exam.TABLE_NAME)
data class Exam(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = FIELD_ID) var id: Int = 0,
    @ColumnInfo(name = FIELD_LIST_QUESTION_ID) var questionIdList: String = "",
    @ColumnInfo(name = FIELD_LIST_ANSWER) var answerList: String = "",
    @ColumnInfo(name = FIELD_TIME) var time: Int = 0
) {
    companion object {
        const val TABLE_NAME = "exam"
        const val FIELD_ID = "id"
        const val FIELD_LIST_QUESTION_ID = "list_question_id"
        const val FIELD_LIST_ANSWER = "list_answer"
        const val FIELD_TIME = "time"
    }
}
