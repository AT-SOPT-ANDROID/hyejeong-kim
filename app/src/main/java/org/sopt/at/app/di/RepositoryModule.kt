package org.sopt.at.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.repository.SeriesRepository
import org.sopt.at.data.repository.SeriesRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // 인터페이스의 인스턴스를 제공해야 할 때 사용할 구현을 Hilt에 알려줌
    @Binds
    abstract fun bindsSeriesRepository(seriesRepositoryImpl: SeriesRepositoryImpl): SeriesRepository
}