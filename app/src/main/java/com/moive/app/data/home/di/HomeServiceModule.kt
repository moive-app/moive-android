package com.moive.app.data.home.di

import com.moive.app.core.network.di.Auth
import com.moive.app.data.home.remote.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeServiceModule {

    @Provides
    @Singleton
    fun provideHomeService(
        @Auth retrofit: Retrofit
    ): HomeService = retrofit.create()
}
