package com.moive.app.data.notification.di

import com.moive.app.data.notification.remote.datasource.NotificationRemoteDataSource
import com.moive.app.data.notification.remote.datasource.NotificationRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNotificationRemoteDataSource(
        notificationRemoteDataSourceImpl: NotificationRemoteDataSourceImpl,
    ): NotificationRemoteDataSource
}
