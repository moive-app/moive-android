package com.moive.app.presentation.meeting.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.presentation.meeting.detail.MeetingDetailContract.MeetingStatus

@Composable
fun MeetingDetailRoute(
    navigateToMeetingConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MeetingDetailScreen(
        status = uiState.status,
        onStatusChange = viewModel::changeStatus,
        onBottomButtonClick = {
            when (it) {
                MeetingStatus.INPUTTING -> {}// Todo: 조건 입력 화면
                MeetingStatus.VOTING -> {}// Todo: 투표 화면
                MeetingStatus.CONFIRMED -> navigateToMeetingConfirmed()
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun MeetingDetailScreen(
    status: MeetingStatus,
    onStatusChange: () -> Unit,
    onBottomButtonClick: (MeetingStatus) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = "모임 상세")

        Button(
            onClick = onStatusChange,
            modifier = Modifier.align(Alignment.TopCenter),
        ) {
            Text(text = "상태 변경 (현재: $status)")
        }

        Button(
            onClick = { onBottomButtonClick(status) },
            enabled = status != MeetingStatus.INPUTTING,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = when (status) {
                    MeetingStatus.INPUTTING -> "아직 조건 입력 중이에요"
                    MeetingStatus.VOTING -> "추천 장소 확인 및 투표"
                    MeetingStatus.CONFIRMED -> "확정된 모임 보러가기"
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingDetailScreenPreview() {
    MoiveTheme {
        MeetingDetailScreen(
            status = MeetingStatus.INPUTTING,
            onStatusChange = {},
            onBottomButtonClick = {},
        )
    }
}

