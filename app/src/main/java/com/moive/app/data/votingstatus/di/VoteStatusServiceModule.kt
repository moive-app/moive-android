package com.moive.app.data.votingstatus.di

import com.moive.app.core.network.di.Auth
import com.moive.app.data.votingstatus.remote.service.VoteStatusService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VoteStatusServiceModule {

    @Provides
    @Singleton
    fun provideVoteStatusService(
        @Auth retrofit: Retrofit,
    ): VoteStatusService = retrofit.create()
}
