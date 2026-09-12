package com.moive.app.data.voting.di

import com.moive.app.data.voting.remote.datasource.VotingRemoteDataSource
import com.moive.app.data.voting.remote.datasource.VotingRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VotingDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindVotingRemoteDataSource(
        votingRemoteDataSourceImpl: VotingRemoteDataSourceImpl,
    ): VotingRemoteDataSource
}
