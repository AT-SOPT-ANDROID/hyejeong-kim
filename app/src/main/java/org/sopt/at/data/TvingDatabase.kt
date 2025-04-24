package org.sopt.at.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.sopt.at.data.dao.SeriesDao
import org.sopt.at.data.local.Series

@Database(entities = [Series::class], version = 1, exportSchema = false)
abstract class TvingDatabase : RoomDatabase() {

    abstract fun seriesDao(): SeriesDao

    companion object {
        @Volatile
        private var Instance: TvingDatabase? = null

        fun getDatabase(context: Context): TvingDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TvingDatabase::class.java, "tving_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}