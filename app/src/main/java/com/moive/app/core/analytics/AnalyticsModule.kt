package com.moive.app.core.analytics

import com.moive.app.core.analytics.amplitude.AmplitudeTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsTracker(
        analyticsTracker: AmplitudeTracker
    ): AnalyticsTracker
}
