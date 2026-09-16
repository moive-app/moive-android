package com.moive.app.presentation.meeting.create

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Immutable
import com.moive.app.core.extensions.checkLength
import com.moive.app.data.meeting.mapper.MeetingPurposeType
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
        val meetingCreationUiState: MeetingCreationUiState = MeetingCreationUiState.Idle,
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

    sealed class SideEffect {
        data class NavigateToMeetingDetail(val meetingId: Long) : SideEffect()
    }

    companion object {
        const val SCHEDULE_CONFIRMED = "네"
        const val SCHEDULE_NOT_CONFIRMED = "아니오"
        private val SCHEDULE_LIST = persistentListOf(SCHEDULE_CONFIRMED, SCHEDULE_NOT_CONFIRMED)
        private val MEETING_PURPOSE_LIST = persistentListOf("친목·만남", "기념·축하", "네트워킹·교류", "스터디·학습", "취미·여가", "기타")
        private val MEETING_NAME_REGEX = Regex("^(?=.*[ㄱ-ㅎ가-힣a-zA-Z0-9])[ㄱ-ㅎ가-힣a-zA-Z0-9 ]+$")
        private const val MEETING_NAME_MAX_LENGTH = 10
        val SCHEDULE_FORMAT_REGEX = Regex(
            """^(20[0-9]{2})년 (0?[1-9]|1[0-2])월 (0?[1-9]|[12][0-9]|3[01])일 ([01][0-9]|2[0-3]):([0-5][0-9])$"""
        )
    }
}

sealed interface MeetingCreationUiState {
    data object Idle: MeetingCreationUiState
    data object Loading: MeetingCreationUiState
    data object Success: MeetingCreationUiState
    data class Failure (
        val msg: String,
    ): MeetingCreationUiState
}

data class ScheduleComponents(
    val year: Int,
    val month: Int,
    val day: Int,
    val hour: Int,
    val minute: Int,
)

fun String.toScheduleComponents(): ScheduleComponents? {
    val match = MeetingCreationContract.SCHEDULE_FORMAT_REGEX.find(this) ?: return null
    val (year, month, day, hour, minute) = match.destructured
    return ScheduleComponents(
        year = year.toInt(),
        month = month.toInt(),
        day = day.toInt(),
        hour = hour.toInt(),
        minute = minute.toInt(),
    )
}

fun String.toScheduledDateTime(): Pair<String, String>? {
    val components = toScheduleComponents() ?: return null
    val scheduledDate = "%04d-%02d-%02d".format(components.year, components.month, components.day)
    val scheduledTime = "%02d:%02d:00".format(components.hour, components.minute)
    return scheduledDate to scheduledTime
}

fun String.toMeetingPurposeType(): MeetingPurposeType? =
    MeetingPurposeType.entries.find { it.label == this }

