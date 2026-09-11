package com.moive.app.data.meeting.di

import com.moive.app.data.meeting.repository.MeetingRepository
import com.moive.app.data.meeting.repository.MeetingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MeetingRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMeetingRepository(
        meetingRepositoryImpl: MeetingRepositoryImpl,
    ): MeetingRepository
}
