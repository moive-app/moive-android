package com.moive.app.presentation.meeting.detail

import androidx.compose.runtime.Immutable

interface MeetingDetailContract {
    @Immutable
    data class State(
        val status: MeetingStatus = MeetingStatus.INPUTTING,
    )

    enum class MeetingStatus {
        INPUTTING,
        VOTING,
        CONFIRMED;
    }

}
