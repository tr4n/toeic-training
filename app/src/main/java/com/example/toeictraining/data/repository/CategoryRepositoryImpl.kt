package com.example.toeictraining.data.repository

import com.example.toeictraining.data.db.AppDatabase
import com.example.toeictraining.data.model.Category
import com.example.toeictraining.utils.Constants

class CategoryRepositoryImpl(
    private val appDatabase: AppDatabase
) : CategoryRepository {

    override suspend fun getCategories(): List<Category> {

        val categoryNames = appDatabase.topicDao().getCategoryNames()
        val topics = appDatabase.topicDao().getTopics()
        return categoryNames.map { name ->
            val subTopics = topics.filter { it.category == name }
            Category(
                name = name,
                color = subTopics.first().color ?: Constants.DEFAULT_COLOR,
                topics = subTopics
            )
        }
    }
}
