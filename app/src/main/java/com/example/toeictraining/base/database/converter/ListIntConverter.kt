package com.example.toeictraining.base.database.converter

import android.util.Log
import androidx.room.TypeConverter

class ListIntConverter {
    @TypeConverter
    fun stringToListInt(value: String?): MutableList<Int> {
        if (value == null) {
            return mutableListOf()
        }
        var string = value
        val list = mutableListOf<Int>()
        do {
            val i = string!!.substring(0, string.indexOf("|"))
            list.add(i.toInt())
            string = string.substring(string.indexOf("|") + 1)

            Log.d("stringToListInt", "id = $i, string = $string")
        } while (string!!.contains("|"))
        return list
    }

    @TypeConverter
    fun listIntToString(list: MutableList<Int>): String? {
        var string = ""
        for (i in list) {
            string.plus("|$i")
        }
        string = string.substring(1)
        Log.d("listIntToString", "string = $string")
        return string
    }
}