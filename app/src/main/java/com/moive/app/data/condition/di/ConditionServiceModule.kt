package com.moive.app.data.condition.di

import com.moive.app.core.network.di.Auth
import com.moive.app.data.condition.remote.service.ConditionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConditionServiceModule {

    @Provides
    @Singleton
    fun provideConditionService(
        @Auth retrofit: Retrofit
    ): ConditionService = retrofit.create()
}
