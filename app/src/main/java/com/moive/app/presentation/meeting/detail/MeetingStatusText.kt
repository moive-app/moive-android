package com.moive.app.presentation.meeting.detail

import com.moive.app.presentation.meeting.detail.MeetingDetailContract.MeetingStatus

fun statusParticipantLabel(status: MeetingStatus, isDone: Boolean): String = when (status) {
    MeetingStatus.INPUTTING -> if (isDone) "조건 입력 완료" else "조건 입력 전"
    MeetingStatus.VOTING -> if (isDone) "투표 완료" else "투표 전"
    MeetingStatus.CONFIRMED -> "모임 확정"
}

fun statusActionButtonText(status: MeetingStatus, isDone: Boolean): String = when (status) {
    MeetingStatus.INPUTTING -> if (isDone) "조건 확정" else "조건 입력하기"
    MeetingStatus.VOTING -> "조건 확정"
    MeetingStatus.CONFIRMED -> "모임 확정"
}

fun statusTooltipText(status: MeetingStatus): String = when (status) {
    MeetingStatus.INPUTTING -> "아직 조건 입력 중이에요!"
    MeetingStatus.VOTING -> "이미 조건이 완료된 모임이에요!"
    MeetingStatus.CONFIRMED -> "모임이 확정됐어요. 모임 정보를 확인해보세요!"
}

fun statusBottomButtonText(status: MeetingStatus): String = when (status) {
    MeetingStatus.INPUTTING -> "추천 장소 확인"
    MeetingStatus.VOTING -> "추천 장소 확인 및 투표"
    MeetingStatus.CONFIRMED -> "확정된 모임 보러가기"
}
