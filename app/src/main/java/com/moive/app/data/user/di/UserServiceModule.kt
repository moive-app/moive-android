package com.moive.app.data.user.di

import com.moive.app.core.network.di.Auth
import com.moive.app.data.user.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserServiceModule {

    @Provides
    @Singleton
    fun provideUserService(
        @Auth retrofit: Retrofit
    ): UserService = retrofit.create()
}
