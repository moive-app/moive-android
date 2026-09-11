package com.moive.app.data.condition.di

import com.moive.app.data.condition.repository.ConditionRepository
import com.moive.app.data.condition.repository.ConditionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConditionRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindConditionRepository(
        conditionRepositoryImpl: ConditionRepositoryImpl,
    ): ConditionRepository
}
