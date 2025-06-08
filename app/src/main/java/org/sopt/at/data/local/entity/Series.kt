package org.sopt.at.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series_table")
data class Series(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val imageUrl: String = ""
)