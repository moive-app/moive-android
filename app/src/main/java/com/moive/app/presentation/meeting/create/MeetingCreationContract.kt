package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.moive.app.core.designsystem.component.toast.ToastType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingCreationContract {
    @Immutable
    data class State(
        val meetingName: TextFieldState = TextFieldState(initialText = ""),
        val meetingSchedule: TextFieldState = TextFieldState(initialText = ""),
        val scheduleConfirmed: ImmutableList<String> = SCHEDULE_LIST,
        val selectedScheduleConfirmed: String = scheduleConfirmed.first(),
        val meetingPurpose: ImmutableList<String> = MEETING_PURPOSE_LIST,
        val selectedMeetingPurpose: String = meetingPurpose.first(),
    )

    companion object {
        private val SCHEDULE_LIST = persistentListOf("네", "아니오")
        private val MEETING_PURPOSE_LIST = persistentListOf("친목·만남", "기념·축하", "네트워킹·교류", "스터디·학습", "취미·여가", "기타")
    }
}
