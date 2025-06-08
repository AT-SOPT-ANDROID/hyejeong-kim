package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.entity.Series

interface SeriesRepository {
    fun getAllSeriesStream(): Flow<List<Series>>

    suspend fun insertSeries(series: Series)
    suspend fun deleteSeries(series: Series)
}