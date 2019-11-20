package com.example.toeictraining.base.database.dao

import androidx.room.TypeConverter
import com.example.toeictraining.base.enums.QuestionType

class QuestionTypeConverter {
    @TypeConverter
    fun stringToQuestionType(value: String?): QuestionType {
        if (value == null) {
            return QuestionType.EASY
        }
        return QuestionType.valueOf(value)
    }

    @TypeConverter
    fun questionTypeToString(questionType: QuestionType): String? {
        return questionType.name
    }
}