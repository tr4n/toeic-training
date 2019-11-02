package com.example.toeictraining.data.db.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = Topic.TABLE_NAME)
@Parcelize
data class Topic(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = FIELD_ID) val id: Int = 0,
    @ColumnInfo(name = FIELD_NAME) val name: String = "",
    @ColumnInfo(name = FIELD_NO) val no: Int = 0,
    @ColumnInfo(name = FIELD_IMAGE_URL) val imageUrl: String = "",
    @ColumnInfo(name = FIELD_CATEGORY) val category: String = "",
    @ColumnInfo(name = FIELD_COLOR) val color: String? = null,
    @ColumnInfo(name = FIELD_LAST_TIME) val lastTime: String? = null
) : Parcelable {

    companion object {
        const val TABLE_NAME = "tbl_topic"
        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
        const val FIELD_NO = "no"
        const val FIELD_IMAGE_URL = "image_url"
        const val FIELD_CATEGORY = "category"
        const val FIELD_COLOR = "color"
        const val FIELD_LAST_TIME = "last_time"
    }
}
