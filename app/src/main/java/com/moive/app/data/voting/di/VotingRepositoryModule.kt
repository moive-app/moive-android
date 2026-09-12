package com.moive.app.data.voting.di

import com.moive.app.data.voting.repository.VotingRepository
import com.moive.app.data.voting.repository.VotingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VotingRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVotingRepository(
        votingRepositoryImpl: VotingRepositoryImpl,
    ): VotingRepository
}
