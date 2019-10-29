package com.example.toeictraining.base.entity

import com.example.toeictraining.base.enums.QuestionType

data class QuestionEntity(
    var id: Int,
    var content: String,
    var a: String,
    var b: String,
    var c: String?,
    var d: String?,
    var soundLink: String?,
    var type: QuestionType,
    var correctAnswer: String,
    var idPart: Int
)