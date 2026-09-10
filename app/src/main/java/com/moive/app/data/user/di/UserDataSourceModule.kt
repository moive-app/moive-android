package com.moive.app.data.user.di

import com.moive.app.data.user.remote.datasource.UserRemoteDataSource
import com.moive.app.data.user.remote.datasource.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl,
    ): UserRemoteDataSource
}
