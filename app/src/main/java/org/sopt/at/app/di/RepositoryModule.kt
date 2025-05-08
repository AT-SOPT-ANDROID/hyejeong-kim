package org.sopt.at.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.repository.SeriesRepository
import org.sopt.at.data.repository.SeriesRepositoryImpl
import org.sopt.at.data.repository.AuthRepository
import org.sopt.at.data.repository.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        userRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    // 인터페이스의 인스턴스를 제공해야 할 때 사용할 구현을 Hilt에 알려줌
    @Binds
    @Singleton
    abstract fun bindsSeriesRepository(seriesRepositoryImpl: SeriesRepositoryImpl): SeriesRepository
}