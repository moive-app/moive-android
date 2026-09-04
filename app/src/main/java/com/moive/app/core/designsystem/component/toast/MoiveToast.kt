package com.moive.app.core.designsystem.component.toast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun MoiveToast(
    text: String,
    type: ToastType,
    modifier: Modifier = Modifier,
) {
    val img = when(type) {
        ToastType.ERROR -> R.drawable.img_toast_error
        ToastType.SUCCESS -> R.drawable.img_toast_success
        ToastType.CAUTION -> R.drawable.img_toast_caution
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.fill.default02,
                shape = RoundedCornerShape(radius.md),
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        Image(
            painter = painterResource(img),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
        )

        Text(
            text = text,
            color = colors.text.onBg,
            style = typography.label.smM,
        )

    }
}

@Preview
@Composable
private fun MoiveToastPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ToastType.entries.forEach { type ->
                MoiveToast(
                    text = "${type.name} 토스트 메세지입니다.",
                    type = type,
                )
            }
        }
    }
}
