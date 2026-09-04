package com.moive.app.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.R.drawable.ic_launcher_background
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveSearchTextField(
    state: TextFieldState,
    placeholder: String,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val hasValue = state.text.isNotEmpty()

    Row(
        modifier = modifier
            .background(
                color = colors.gray01,
                shape = RoundedCornerShape(radius.sm),
            )
            .padding(vertical = 9.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!hasValue) {
            Icon(
                imageVector = ImageVector.vectorResource(ic_launcher_background),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }

        MoiveBasicTextField(
            state = state,
            textColor = colors.gray800,
            textStyle = typography.body.smNormalR,
            placeholder = placeholder,
            placeholderColor = colors.gray300,
            placeholderStyle = typography.body.smNormalR,
            modifier = Modifier.weight(1f),
            keyboardOptions = keyboardOptions,
            onKeyboardAction = {
                onSearch()
                focusManager.clearFocus()
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            interactionSource = interactionSource,
        )

        if (hasValue) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(ic_launcher_background),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
                    .noRippleClickable(onClick = state::clearText),
            )
        }
    }
}


@Preview
@Composable
private fun MoiveSearchTextFieldPreview() {
    val state = rememberTextFieldState(initialText = "")
    MoiveTheme {
        MoiveSearchTextField(
            state = state,
            placeholder = "장소를 검색해주세요.",
            onSearch = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}
