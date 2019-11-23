package com.example.toeictraining.base.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toeictraining.base.database.dao.*
import com.example.toeictraining.base.entity.*
import com.huma.room_for_asset.RoomAsset

private const val DATABASE_NAME = "toeic_600.db"
private const val DATABASE_VERSION = 2

@Database(
    entities = [Topic::class, Word::class, Question2::class, Part::class, Exam::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun topicDao(): TopicDao
    abstract fun questionDao(): QuestionDao
    abstract fun partDao(): PartDao
    abstract fun examDao(): ExamDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: getDatabase(context).also { INSTANCE = it }

        private fun getDatabase(context: Context): AppDatabase =
            RoomAsset.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
