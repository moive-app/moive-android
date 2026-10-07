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

    data class MeetingJoined(
        val meetingId: Long,
    ) : AnalyticsEvent {
        override val name = "Meeting Joined"
        override val properties: Map<String, Any?>
            get() = mapOf(AnalyticsPropertyKey.MEETING_ID to meetingId)
    }

    // 조건 입력
    data class ConditionStepViewed(
        val meetingId: Long,
        val step: String,
    ) : AnalyticsEvent {
        override val name = "Condition Step Viewed"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.STEP to step,
            )
    }

    data class ConditionSubmitted(
        val meetingId: Long,
    ) : AnalyticsEvent {
        override val name = "Condition Submitted"
        override val properties: Map<String, Any?>
            get() = mapOf(AnalyticsPropertyKey.MEETING_ID to meetingId)
    }

    // 장소 투표
    data class RecommendedAreaSelected(
        val meetingId: Long,
    ) : AnalyticsEvent {
        override val name = "Recommended Area Selected"
        override val properties: Map<String, Any?>
            get() = mapOf(AnalyticsPropertyKey.MEETING_ID to meetingId)
    }

    data class PlaceDetailViewed(
        val meetingId: Long,
        val placeId: Long,
    ) : AnalyticsEvent {
        override val name = "Place Detail Viewed"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.PLACE_ID to placeId,
            )
    }

    data class PlaceRouteOpened(
        val meetingId: Long,
        val placeId: Long,
    ) : AnalyticsEvent {
        override val name = "Place Route Opened"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.PLACE_ID to placeId,
            )
    }

    // 알림
    data class PushNotificationOpened(
        val notificationId: Long?,
        val meetingId: Long?,
    ) : AnalyticsEvent {
        override val name = "Push Notification Opened"
        override val properties: Map<String, Any?>
            get() = buildMap {
                notificationId?.let { put(AnalyticsPropertyKey.NOTIFICATION_ID, it) }
                meetingId?.let { put(AnalyticsPropertyKey.MEETING_ID, it) }
            }
    }

    data class PlaceVoteSubmitted(
        val meetingId: Long,
        val selectedCount: Int,
    ) : AnalyticsEvent {
        override val name = "Place Vote Submitted"
        override val properties: Map<String, Any?>
            get() = mapOf(
                AnalyticsPropertyKey.MEETING_ID to meetingId,
                AnalyticsPropertyKey.SELECTED_COUNT to selectedCount,
            )
    }
}
