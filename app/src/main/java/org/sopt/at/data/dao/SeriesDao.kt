package org.sopt.at.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.Series

@Dao
interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: Series)

    @Delete
    suspend fun deleteSeries(series: Series)

    @Query("SELECT * from series_table ORDER BY title ASC")
    fun getAllItems(): Flow<List<Series>>
}