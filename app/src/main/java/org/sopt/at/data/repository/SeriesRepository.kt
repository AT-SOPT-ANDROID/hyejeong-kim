package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.dao.SeriesDao
import org.sopt.at.data.local.Series

interface SeriesRepository {
    fun getAllSeriesStream(): Flow<List<Series>>

    suspend fun insertSeries(series: Series)
    suspend fun deleteSeries(series: Series)
}