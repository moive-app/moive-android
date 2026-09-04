package com.moive.app.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.extensions.customShadow
import com.moive.app.core.extensions.noRippleClickable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainBottomBar(
    isVisible: Boolean,
    tabs: ImmutableList<MainTab>,
    currentTab: MainTab?,
    onMeetingBtnClick: () -> Unit,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) },
    ) {
        Box(
            modifier = modifier.navigationBarsPadding(),
            contentAlignment = Alignment.Center,
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .customShadow(
                        shape = RoundedCornerShape(
                            topStart = radius.xxl,
                            topEnd = radius.xxl,
                        ),
                        color = colors.shadow8,
                        blur = 15.dp,
                    )
                    .background(
                        color = colors.fill.default08,
                        shape = RoundedCornerShape(
                            topStart = radius.xxl,
                            topEnd = radius.xxl,
                        )
                    )
                    .padding(horizontal = 46.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEach { tab ->
                    key(tab.route) {
                        MainBottomBarItem(
                            tab = tab,
                            isSelected = tab == currentTab,
                            onClick = { onTabSelected(tab) },
                        )
                    }
                }
            }

            MeetingFloatingButton(
                onMeetingBtnClick = onMeetingBtnClick,
            )
        }
    }
}

@Composable
private fun MainBottomBarItem(
    tab: MainTab,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = ImageVector.vectorResource(tab.iconRes),
        contentDescription = tab.titleRes,
        tint = if (isSelected) colors.icon.default else colors.icon.disabled,
        modifier = modifier.noRippleClickable(onClick),
    )
}

@Composable
private fun MeetingFloatingButton(
    onMeetingBtnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .customShadow(
                shape = RoundedCornerShape(radius.circular),
                color = colors.shadow20,
                blur = 10.dp,
                offsetY = 4.dp,
            )
            .background(
                color = colors.fill.default00,
                shape = RoundedCornerShape(radius.circular)
            )
            .padding(16.dp)
            .noRippleClickable(onClick = onMeetingBtnClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_union),
            contentDescription = null,
            tint = colors.icon.onBg,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF1F3F5)
@Composable
private fun MainBottomBarPreview() {
    MoiveTheme {
        var currentTab by remember { mutableStateOf(MainTab.HOME) }

        Box(
            modifier = Modifier.padding(30.dp),
            contentAlignment = Alignment.Center,
        ) {
            MainBottomBar(
                isVisible = true,
                tabs = persistentListOf(MainTab.HOME, MainTab.MYPAGE),
                currentTab = currentTab,
                onMeetingBtnClick = {},
                onTabSelected = { currentTab = it },
            )
        }
    }
}
