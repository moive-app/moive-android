package com.moive.app.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

@Composable
fun MoiveLabelChip(
    style: LabelStyle,
    text: String,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = style.borderColor,
                shape = RoundedCornerShape(radius.circular),
            )
            .background(
                color = style.backgroundColor,
                shape = RoundedCornerShape(radius.circular)
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
    ) {
        Text(
            text = text,
            color = style.contentColor,
            style = typography.label.xsM,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveLabelChipPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            LabelType.entries.chunked(3).forEach { rowTypes ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    rowTypes.forEach { type ->
                        MoiveLabelChip(
                            style = type.getStyle(),
                            text = type.name,
                        )
                    }
                }
            }
        }
    }
}
