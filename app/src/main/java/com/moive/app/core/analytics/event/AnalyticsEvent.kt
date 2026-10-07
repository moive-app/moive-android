package com.moive.app.core.analytics.event

sealed interface AnalyticsEvent {
    val name: String
    val properties: Map<String, Any?>
        get() = emptyMap()

    data class ScreenViewed(
        val screenName: String,
        val meetingId: Long? = null,
    ) : AnalyticsEvent {
        override val name = "Screen Viewed"
        override val properties: Map<String, Any?>
            get() = buildMap {
                put(AnalyticsPropertyKey.SCREEN_NAME, screenName)
                meetingId?.let { put(AnalyticsPropertyKey.MEETING_ID, it) }
            }
    }
}
