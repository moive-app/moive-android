package com.moive.app.data.condition.di

import com.moive.app.data.condition.remote.datasource.PlaceRemoteDataSource
import com.moive.app.data.condition.remote.datasource.PlaceRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KakaoPlaceDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindPlaceRemoteDataSource(
        placeRemoteDataSourceImpl: PlaceRemoteDataSourceImpl,
    ): PlaceRemoteDataSource
}
