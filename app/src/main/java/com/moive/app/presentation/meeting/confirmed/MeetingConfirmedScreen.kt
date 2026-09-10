package com.moive.app.presentation.meeting.confirmed

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.shareText
import com.moive.app.presentation.common.component.ShadowButton
import com.moive.app.presentation.meeting.confirmed.component.PlaceTimeRow
import com.moive.app.presentation.meeting.confirmed.component.TravelTimeCard

@Composable
fun MeetingConfirmedRoute(
    innerPadding: PaddingValues,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MeetingConfirmedViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    MeetingConfirmedScreen(
        innerPadding = innerPadding,
        uiState = uiState,
        onBackClick = navigateBack,
        onPlaceClick = {},
        onShareClick = { context.shareText("모임에 참여해보세요!\n${uiState.meetingLink}") },
        modifier = modifier,
    )
}

@Composable
private fun MeetingConfirmedScreen(
    innerPadding: PaddingValues,
    uiState: MeetingConfirmedContract.State,
    onBackClick: () -> Unit,
    onPlaceClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val isContentScrollable by remember {
        derivedStateOf { lazyListState.canScrollForward || lazyListState.canScrollBackward }
    }

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
            state = lazyListState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 48.dp),
        ) {
            item {
                Text(
                    text = "모임이 확정되었어요!",
                    color = colors.text.default,
                    style = typography.title.lgB,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(
                            if (uiState.isPlaceConfirmed) 320f / 167f else 320f / 247f
                        )
                )

                Spacer(modifier = Modifier.height(16.dp))

                PlaceTimeRow(
                    isPlaceConfirmed = uiState.isPlaceConfirmed,
                    placeName = uiState.placeName,
                    placeCategory = uiState.placeCategory,
                    placeAddress = uiState.placeAddress,
                    meetingDate = uiState.meetingDate,
                    meetingTime = uiState.meetingTime,
                    onPlaceClick = onPlaceClick,
                )

                if (uiState.isPlaceConfirmed) {
                    Spacer(modifier = Modifier.height(8.dp))

                    TravelTimeCard(
                        participants = uiState.participants
                    )
                }
            }
        }

        ShadowButton(
            text = "친구에게 공유하기",
            isEnabled = true,
            onClick = onShareClick,
            showShadow = isContentScrollable,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MeetingConfirmedScreenPreview() {
    MoiveTheme {
        MeetingConfirmedScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingConfirmedContract.State(),
            onBackClick = {},
            onPlaceClick = {},
            onShareClick = {},
        )
    }
}

@Preview(showBackground = true, name = "장소 미투표")
@Composable
private fun MeetingConfirmedScreenPlaceUndecidedPreview() {
    MoiveTheme {
        MeetingConfirmedScreen(
            innerPadding = PaddingValues(),
            uiState = MeetingConfirmedContract.State(
                isPlaceConfirmed = false,
            ),
            onBackClick = {},
            onPlaceClick = {},
            onShareClick = {},
        )
    }
}
