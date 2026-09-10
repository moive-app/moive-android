package com.moive.app.data.condition.di

import com.moive.app.data.condition.repository.PlaceRepository
import com.moive.app.data.condition.repository.PlaceRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class KakaoPlaceRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl,
    ): PlaceRepository
}
