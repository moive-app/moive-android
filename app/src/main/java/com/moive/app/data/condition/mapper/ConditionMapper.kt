package com.moive.app.data.condition.mapper

import com.moive.app.data.condition.model.AvailableScheduleModel
import com.moive.app.data.condition.model.ConditionModel
import com.moive.app.data.condition.remote.dto.AvailableScheduleRequest
import com.moive.app.data.condition.remote.dto.ConditionResponse
import kotlinx.serialization.Serializable

fun ConditionResponse.toModel(): ConditionModel =
    ConditionModel(
        meetingId = meetingId,
        participantId = participantId,
        participantState = participantState,
        participantStateLabel = participantStateLabel,
        meetingStatus = meetingStatus,
        recommendationTriggered = recommendationTriggered,
    )

fun AvailableScheduleModel.toRequest(): AvailableScheduleRequest =
    AvailableScheduleRequest(date = date, time = time)

@Serializable
enum class ActivityType(val label: String) {
    BOARD_GAME("보드게임"),
    BOWLING("볼링"),
    ESCAPE_ROOM("방탈출"),
    KARAOKE("노래방"),
    PC_ROOM("PC방"),
    COMIC_CAFE("만화카페"),
    MOVIE("영화관"),
    PERFORMANCE("공연"),
    EXHIBITION("전시·미술관"),
    MUSEUM("박물관"),
    KOREAN_FOOD("한식"),
    WESTERN_FOOD("양식"),
    CHINESE_FOOD("중식"),
    JAPANESE_FOOD("일식"),
    MEAT("고기"),
    SEAFOOD("해산물"),
    CAFE_DESSERT("카페·디저트"),
    PARK("공원"),
    WALK("산책"),
    HIKING("등산"),
    SHOPPING("쇼핑"),
}
