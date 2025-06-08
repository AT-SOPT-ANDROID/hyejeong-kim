package org.sopt.at.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.TvingDatabase
import org.sopt.at.data.local.dao.SeriesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): TvingDatabase = Room
        .databaseBuilder(context, TvingDatabase::class.java, "tving_database")
        .build()

    @Provides
    @Singleton
    fun provideSeriesDao(tvingDatabase: TvingDatabase): SeriesDao = tvingDatabase.seriesDao()
}