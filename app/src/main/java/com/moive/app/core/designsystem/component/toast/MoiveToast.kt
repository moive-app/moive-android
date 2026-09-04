package com.moive.app.core.designsystem.component.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme

@Composable
fun MoiveToast(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MoiveTheme.colors.fill.default02,
                shape = RoundedCornerShape(6.dp),
            )
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = text,
            color = MoiveTheme.colors.text.onBg,
            modifier = Modifier.padding(vertical = 11.dp)
        )

    }
}

@Preview
@Composable
private fun MoiveToastPreview() {
    MoiveTheme{
        MoiveToast(
            text = "토스트 메세지입니다.",
        )
    }
}
