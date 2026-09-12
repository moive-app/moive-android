package com.moive.app.data.votingstatus.di

import com.moive.app.data.votingstatus.repository.VoteStatusRepository
import com.moive.app.data.votingstatus.repository.VoteStatusRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VoteStatusRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVoteStatusRepository(
        voteStatusRepositoryImpl: VoteStatusRepositoryImpl,
    ): VoteStatusRepository
}
