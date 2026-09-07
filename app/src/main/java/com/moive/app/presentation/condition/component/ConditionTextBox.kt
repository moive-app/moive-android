package com.moive.app.presentation.condition.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun ConditionTextBox(
    placeholder: String,
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colors.stroke.default03,
                shape = RoundedCornerShape(radius.sm),
            )
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.sm),
            )
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 9.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                tint = colors.icon.disabled,
            )
        }

        Text(
            text = text ?: placeholder,
            color = if (text != null) colors.text.default else colors.text.disabled,
            style = typography.body.smNormalR,
            modifier = Modifier.weight(1f),
        )

        if (trailingIcon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null,
                tint = colors.icon.tertiary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConditionTextBoxPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ConditionTextBox(
                placeholder = "가능한 날짜를 모두 선택해주세요",
                text = null,
                trailingIcon = R.drawable.ic_calendar_20,
                onClick = {},
            )
            ConditionTextBox(
                placeholder = "위치를 설정해주세요",
                text = "진흥아파트",
                leadingIcon = R.drawable.ic_search_20,
                onClick = {},
            )
        }
    }
}
