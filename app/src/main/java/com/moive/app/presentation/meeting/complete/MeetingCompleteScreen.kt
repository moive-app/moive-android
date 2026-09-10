package com.moive.app.presentation.meeting.complete

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moive.app.R
import com.moive.app.core.designsystem.component.topbar.MoiveSubIconTopBar
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.presentation.meeting.complete.component.CompletedParticipantsCard
import com.moive.app.presentation.meeting.complete.component.CompletedPlaceCard

@Composable
fun MeetingCompleteRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingCompleteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MeetingCompleteScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        modifier = modifier,
    )
}

@Composable
private fun MeetingCompleteScreen(
    innerPadding: PaddingValues,
    uiState: MeetingCompleteContract.State,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background.default02)
            .padding(innerPadding),
    ) {
        MoiveSubIconTopBar(
            onLeadingIconClick = onBackClick,
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 48.dp),
        ) {
            item {
                Text(
                    text = "완료된 모임입니다!",
                    color = colors.text.default,
                    style = typography.title.lgB,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (uiState.isPlaceConfirmed) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(320f / 167f)
                            .clip(RoundedCornerShape(radius.xl)),
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                CompletedPlaceCard(
                    isPlaceConfirmed = uiState.isPlaceConfirmed,
                    placeName = uiState.placeName,
                    placeCategory = uiState.placeCategory,
                    placeAddress = uiState.placeAddress,
                    meetingDate = uiState.meetingDate,
                    meetingTime = uiState.meetingTime,
                )

                Spacer(modifier = Modifier.height(8.dp))

                CompletedParticipantsCard(
                    participants = uiState.participants,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingCompleteScreenPreview() {
    MoiveTheme {
        MeetingCompleteScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingCompleteContract.State(),
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, name = "장소 미정")
@Composable
private fun MeetingCompleteScreenPlaceUndecidedPreview() {
    MoiveTheme {
        MeetingCompleteScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingCompleteContract.State(
                isPlaceConfirmed = false,
            ),
            onBackClick = {},
        )
    }
}
