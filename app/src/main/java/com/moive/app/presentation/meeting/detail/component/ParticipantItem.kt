package com.moive.app.presentation.meeting.detail.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.component.button.MoiveButton
import com.moive.app.core.designsystem.component.button.MoiveButtonSize
import com.moive.app.core.designsystem.component.button.MoiveButtonType
import com.moive.app.core.designsystem.component.image.UrlImage
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.data.meeting.model.ParticipantItemModel
import com.moive.app.presentation.meeting.detail.MeetingDetailContract.MeetingStatus
import com.moive.app.presentation.meeting.detail.statusActionButtonText
import com.moive.app.presentation.meeting.detail.statusParticipantLabel

@Composable
fun ParticipantItem(
    participant: ParticipantItemModel,
    status: MeetingStatus,
    onActionButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (participant.isMe) colors.stroke.default03 else colors.stroke.default04,
                shape = RoundedCornerShape(radius.md),
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            UrlImage(
                url = participant.profileImageUrl,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(radius.circular)),
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = if (participant.isMe) "${participant.name}(나)" else participant.name,
                    color = colors.text.default,
                    style = typography.title.smSb,
                )

                Text(
                    text = statusParticipantLabel(status, participant.isDone),
                    color = colors.text.tertiary,
                    style = typography.label.smR,
                )
            }
        }

        if (participant.isMe) {
            MoiveButton(
                text = statusActionButtonText(status, participant.isDone),
                type = MoiveButtonType.SECONDARY,
                size = MoiveButtonSize.XSMALL,
                enabled = if (status == MeetingStatus.INPUTTING) !participant.isDone else false,
                onClick = onActionButtonClick,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
