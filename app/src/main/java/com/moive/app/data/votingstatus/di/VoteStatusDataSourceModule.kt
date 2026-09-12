package com.moive.app.data.votingstatus.di

import com.moive.app.data.votingstatus.remote.datasource.VoteStatusRemoteDataSource
import com.moive.app.data.votingstatus.remote.datasource.VoteStatusRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VoteStatusDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindVoteStatusRemoteDataSource(
        voteStatusRemoteDataSourceImpl: VoteStatusRemoteDataSourceImpl,
    ): VoteStatusRemoteDataSource
}
