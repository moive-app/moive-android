package com.moive.app.presentation.notification

import androidx.compose.runtime.Immutable
import com.moive.app.data.notification.model.NotificationItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface NotificationContract {
    @Immutable
    data class State(
        val notifications: ImmutableList<NotificationItemModel> = persistentListOf(
            NotificationItemModel(
                id = 1L,
                meetingId = 1L,
                title = "조건 입력을 완료해주세요",
                description = "'주말 맛집 모임'의 조건을 아직 입력하지 않았어요.",
                time = "10분 전",
                isRead = false,
            ),
            NotificationItemModel(
                id = 2L,
                meetingId = 2L,
                title = "모임 일정과 장소가 결정됐어요",
                description = "'강남에서 만나자' 모임이 결정됐어요. 바로 확인해보세요!",
                time = "1시간 전",
                isRead = false,
            ),
            NotificationItemModel(
                id = 3L,
                meetingId = 3L,
                title = "장소 추천이 완료됐어요.ssssssssssssssssssssssssssssssss",
                description = "'목동찟'의 장소 추천이 완료됐어요. 지금 투표하세요!\nsdfsdfdfssfsdfsdf\nsfsdffff\nfffffffffffffffff",
                time = "3시간 전",
                isRead = true,
            ),
            NotificationItemModel(
                id = 4L,
                meetingId = 4L,
                title = "장소 투표를 완료해주세요.",
                description = "'목동찟'의 장소 투표를 아직 완료하지 않았어요.",
                time = "3시간 전",
                isRead = true,
            ),
        ),
        val isNotificationPermissionGranted: Boolean = false,
    )
}
