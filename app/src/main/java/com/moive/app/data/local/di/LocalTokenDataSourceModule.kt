package com.moive.app.data.local.di

import com.moive.app.data.local.token.LocalTokenDataSource
import com.moive.app.data.local.token.LocalTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalTokenDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalTokenDataSource(
        localTokenDataSourceImpl: LocalTokenDataSourceImpl,
    ): LocalTokenDataSource
}
