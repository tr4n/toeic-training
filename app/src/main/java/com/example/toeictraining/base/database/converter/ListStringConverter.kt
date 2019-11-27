package com.example.toeictraining.base.database.converter

import android.util.Log
import androidx.room.TypeConverter

class ListStringConverter {
    @TypeConverter
    fun stringToListString(value: String?): MutableList<String> {
        if (value == null) {
            return mutableListOf()
        }
        var string = value
        val list = mutableListOf<String>()
        do {
            val s = string!!.substring(0, string.indexOf("|||"))
            list.add(s)
            string = string.substring(string.indexOf("|||") + 1)

            Log.d("stringToListString", "s = $s, string = $string")
        } while (string!!.contains("|"))
        return list
    }

    @TypeConverter
    fun listStringToString(list: MutableList<String>): String? {
        var string = ""
        for (s in list) {
            string.plus("|$s")
        }
        string = string.substring(1)
        Log.d("listStringToString", "string = $string")
        return string
    }
}