package com.moive.app.data.home.di

import com.moive.app.data.home.remote.datasource.HomeRemoteDataSource
import com.moive.app.data.home.remote.datasource.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindHomeRemoteDataSource(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl,
    ): HomeRemoteDataSource
}
