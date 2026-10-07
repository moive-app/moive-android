package com.moive.app.core.analytics.amplitude

import com.amplitude.android.Amplitude
import com.moive.app.core.analytics.AnalyticsTracker
import com.moive.app.core.analytics.event.AnalyticsEvent
import javax.inject.Inject

class AmplitudeTracker @Inject constructor(
    private val amplitude: Amplitude,
) : AnalyticsTracker {

    override fun track(event: AnalyticsEvent) {
        amplitude.track(event.name, event.properties)
    }

    override fun setUserId(userId: Long) {
        amplitude.setUserId(userId.toString())
    }

    override fun reset() {
        amplitude.reset()
    }
}
