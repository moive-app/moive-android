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

    data class MeetingCreated(
        val meetingId: Long,
        val purpose: String,
        val hasSchedule: Boolean,
    ) : AnalyticsEvent {
        override val name = "Meeting Created"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.PURPOSE to purpose,
                AnalyticsPropertyKey.HAS_SCHEDULE to hasSchedule,
            )
    }

    // 초대
    data class InviteLinkShared(
        val meetingId: Long,
        val source: Source,
    ) : AnalyticsEvent {
        override val name = "Invite Link Shared"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.SOURCE to source.value,
            )

        enum class Source(val value: String) {
            DETAIL("detail"),
            CONFIRMED("confirmed"),
        }
    }
}
