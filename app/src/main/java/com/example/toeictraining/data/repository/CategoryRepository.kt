package com.example.toeictraining.data.repository

import com.example.toeictraining.data.model.Category

interface CategoryRepository {

    suspend fun getCategories(): List<Category>
}
