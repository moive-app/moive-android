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

    // 가입
    data object SignUpCompleted : AnalyticsEvent {
        override val name = "Sign Up Completed"
    }

    // 모임 생성
    data class MeetingCreationStepViewed(
        val step: String,
    ) : AnalyticsEvent {
        override val name = "Meeting Creation Step Viewed"
        override val properties: Map<String, Any?>
            get() = mapOf(AnalyticsPropertyKey.STEP to step)
    }
}
