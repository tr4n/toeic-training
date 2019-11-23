package com.example.toeictraining.base.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Question2.TABLE_NAME)
data class Question2(
    @PrimaryKey @ColumnInfo(name = FIELD_ID) var id: Int,
    @ColumnInfo(name = FIELD_CONTENT) var content: String,
    @ColumnInfo(name = FIELD_A) var a: String,
    @ColumnInfo(name = FIELD_B) var b: String,
    @ColumnInfo(name = FIELD_C) var c: String,
    @ColumnInfo(name = FIELD_D) var d: String?,
    @ColumnInfo(name = FIELD_SOUND_LINK) var soundLink: String?,
    @ColumnInfo(name = FIELD_TYPE) var type: String,
    @ColumnInfo(name = FIELD_CORRECT_ANSWER) var correctAnswer: String,
    @ColumnInfo(name = FIELD_ID_PART) var idPart: Int
) {
    companion object {
        const val TABLE_NAME = "question"
        const val FIELD_ID = "id"
        const val FIELD_CONTENT = "content"
        const val FIELD_A = "a"
        const val FIELD_B = "b"
        const val FIELD_C = "c"
        const val FIELD_D = "d"
        const val FIELD_SOUND_LINK = "soundLink"
        const val FIELD_TYPE = "type"
        const val FIELD_CORRECT_ANSWER = "correctAnswer"
        const val FIELD_ID_PART = "idPart"
    }
}
