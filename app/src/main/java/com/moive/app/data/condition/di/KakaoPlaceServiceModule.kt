package com.moive.app.data.condition.di

import com.moive.app.core.network.di.KakaoLocal
import com.moive.app.data.condition.remote.service.KakaoLocalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoPlaceServiceModule {

    @Provides
    @Singleton
    fun provideKakaoLocalService(
        @KakaoLocal retrofit: Retrofit,
    ): KakaoLocalService = retrofit.create()
}
