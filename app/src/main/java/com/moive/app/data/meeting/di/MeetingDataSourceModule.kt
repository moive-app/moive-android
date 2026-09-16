package com.moive.app.data.meeting.di

import com.moive.app.data.meeting.remote.datasource.MeetingRemoteDataSource
import com.moive.app.data.meeting.remote.datasource.MeetingRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MeetingDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMeetingRemoteDataSource(
        meetingRemoteDataSourceImpl: MeetingRemoteDataSourceImpl,
    ): MeetingRemoteDataSource
}
