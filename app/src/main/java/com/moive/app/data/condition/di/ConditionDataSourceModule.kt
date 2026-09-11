package com.moive.app.data.condition.di

import com.moive.app.data.condition.remote.datasource.ConditionRemoteDataSource
import com.moive.app.data.condition.remote.datasource.ConditionRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConditionDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindConditionRemoteDataSource(
        conditionRemoteDataSourceImpl: ConditionRemoteDataSourceImpl,
    ): ConditionRemoteDataSource
}
