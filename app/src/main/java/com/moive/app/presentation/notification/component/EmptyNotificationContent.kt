package com.moive.app.presentation.notification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun EmptyNotificationContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "새로운 알림이 없어요.",
            color = colors.text.tertiary,
            style = typography.body.smNormalR,
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyNotificationContentPreview() {
    MoiveTheme {
        EmptyNotificationContent()
    }
}
