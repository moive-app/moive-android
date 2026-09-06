package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import com.moive.app.core.designsystem.component.toast.ToastType
import com.moive.app.core.extensions.checkLength
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

interface MeetingCreationContract {
    @Immutable
    data class State(
        val step: Step = Step.CREATE,
        val meetingName: TextFieldState = TextFieldState(initialText = ""),
        val meetingSchedule: TextFieldState = TextFieldState(initialText = ""),
        val scheduleConfirmed: ImmutableList<String> = SCHEDULE_LIST,
        val selectedScheduleConfirmed: String = "",
        val meetingPurpose: ImmutableList<String> = MEETING_PURPOSE_LIST,
        val selectedMeetingPurpose: String = "",
    ) {
        val isMeetingNameInvalid: Boolean
            get() = meetingName.text.isNotEmpty() &&
                (
                    !MEETING_NAME_REGEX.matches(meetingName.text) ||
                        meetingName.text.toString().checkLength() > MEETING_NAME_MAX_LENGTH
                )

        val isMeetingScheduleFormatInvalid: Boolean
            get() = meetingSchedule.text.isNotEmpty() &&
                !SCHEDULE_FORMAT_REGEX.matches(meetingSchedule.text)

        val isNextButtonEnabled: Boolean
            get() = meetingName.text.isNotBlank() &&
                !isMeetingNameInvalid &&
                selectedScheduleConfirmed.isNotEmpty() &&
                (
                    selectedScheduleConfirmed != "네" ||
                        (meetingSchedule.text.isNotEmpty() && !isMeetingScheduleFormatInvalid)
                ) &&
                selectedMeetingPurpose.isNotEmpty()
    }

    enum class Step {
        CREATE,
        CONFIRM,
    }

    companion object {
        private val SCHEDULE_LIST = persistentListOf("네", "아니오")
        private val MEETING_PURPOSE_LIST = persistentListOf("친목·만남", "기념·축하", "네트워킹·교류", "스터디·학습", "취미·여가", "기타")
        private val MEETING_NAME_REGEX = Regex("^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$")
        private const val MEETING_NAME_MAX_LENGTH = 10
        private val SCHEDULE_FORMAT_REGEX = Regex(
            """^(0?[1-9]|1[0-2])월 (0?[1-9]|[12][0-9]|3[01])일 ([01][0-9]|2[0-3]):[0-5][0-9]$"""
        )
    }
}
