package com.moive.app.presentation.meeting.confirmed.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.data.meeting.model.MeetingResultParticipantModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun TravelTimeCard(
    participants: ImmutableList<MeetingResultParticipantModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.xl),
            )
            .padding(vertical = 16.dp, horizontal = 20.dp),
    ) {
        Text(
            text = "이동 소요시간",
            color = colors.text.default,
            style = typography.title.mdSb,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            participants.forEach { participant ->
                TravelParticipantItem(
                    participant = participant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TravelTimeCardPreview() {
    MoiveTheme {
        TravelTimeCard(
            participants = persistentListOf(
                MeetingResultParticipantModel(id = 1L, name = "다인", profileImageUrl = "", address = "서울시 구로구 머시기", transferCount = 1, travelMinutes = 32),
                MeetingResultParticipantModel(id = 2L, name = "수현", profileImageUrl = "", address = "경기도 수원시 고색동", transferCount = 2, travelMinutes = 38),
                MeetingResultParticipantModel(id = 3L, name = "민주", profileImageUrl = "", address = "경기도 수원시 OO동", transferCount = 2, travelMinutes = 52),
                MeetingResultParticipantModel(id = 4L, name = "혜지", profileImageUrl = "", address = "서울시 구로구 머시기", transferCount = 0, travelMinutes = 48),
                MeetingResultParticipantModel(id = 5L, name = "지민", profileImageUrl = "", address = "서울시 구로구 머시기", transferCount = 0, travelMinutes = 50),
            ),
        )
    }
}
