package com.moive.app.core.analytics

import android.content.Context
import com.amplitude.android.Amplitude
import com.amplitude.android.AutocaptureOption.APP_LIFECYCLES
import com.amplitude.android.AutocaptureOption.SESSIONS
import com.amplitude.android.autocaptureOptions
import com.moive.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AmplitudeModule {

    @Provides
    @Singleton
    fun provideAmplitude(
        @ApplicationContext context: Context,
    ): Amplitude = Amplitude(
        apiKey = BuildConfig.AMPLITUDE_API_KEY,
        context = context
    ) {
        autocapture = autocaptureOptions {
            +sessions
            +appLifecycles
        }
    }
}
