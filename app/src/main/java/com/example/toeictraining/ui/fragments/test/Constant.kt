package com.example.toeictraining.ui.fragments.test

class Constant {
    companion object {
        val IDS_PART = arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
        val TIMES_PART = arrayOf(
            0,
            360,
            1500,
            2340,
            1800,
            1800,
            960,
            3240,
            7200
        )
        val QUESTIONS_OF_PART = arrayOf(
            (1..6).toList().toIntArray(),
            (7..31).toList().toIntArray(),
            (32..70).toList().toIntArray(),
            (71..100).toList().toIntArray(),
            (101..130).toList().toIntArray(),
            (131..146).toList().toIntArray(),
            (147..200).toList().toIntArray(),
            (1..200).toList().toIntArray()
        )
    }
}