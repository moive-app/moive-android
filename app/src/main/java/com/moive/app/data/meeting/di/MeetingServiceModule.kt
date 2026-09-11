package com.moive.app.data.meeting.di

import com.moive.app.core.network.di.Auth
import com.moive.app.data.meeting.remote.service.MeetingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MeetingServiceModule {

    @Provides
    @Singleton
    fun provideMeetingService(
        @Auth retrofit: Retrofit
    ): MeetingService = retrofit.create()
}
