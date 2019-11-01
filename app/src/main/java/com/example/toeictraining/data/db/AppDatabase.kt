package com.example.toeictraining.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.toeictraining.data.db.dao.TopicDao
import com.example.toeictraining.data.db.dao.WordDao
import com.example.toeictraining.data.db.entity.Topic
import com.example.toeictraining.data.db.entity.Word
import com.huma.room_for_asset.RoomAsset

private const val DATABASE_NAME = "toeic_600.db"
private const val DATABASE_VERSION = 2

@Database(entities = [Topic::class, Word::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun topicDao(): TopicDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: getDatabase(context).also { INSTANCE = it }

        private fun getDatabase(context: Context): AppDatabase =
            RoomAsset.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
