package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.dao.SeriesDao
import org.sopt.at.data.local.entity.Series
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val seriesDao: SeriesDao
) : SeriesRepository{
    override fun getAllSeriesStream(): Flow<List<Series>> = seriesDao.getAllItems()

    override suspend fun insertSeries(series: Series) = seriesDao.insertSeries(series)

    override suspend fun deleteSeries(series: Series) = seriesDao.deleteSeries(series)
}