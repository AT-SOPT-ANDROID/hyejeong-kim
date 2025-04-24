package org.sopt.at.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series_table")
data class Series(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val imageUrl: String = ""
)