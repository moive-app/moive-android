package com.moive.app.core.analytics

import com.moive.app.core.analytics.event.AnalyticsEvent

interface AnalyticsTracker {
    fun track(event: AnalyticsEvent)
}
