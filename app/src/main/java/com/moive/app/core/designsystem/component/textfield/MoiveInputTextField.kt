package com.moive.app.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.moive.app.R
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography
import com.moive.app.core.extensions.noRippleClickable

@Composable
fun MoiveInputTextField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current
    val hasValue = state.text.isNotEmpty()

    Row(
        modifier = modifier
            .border(
                color = colors.stroke.default03,
                width = 1.dp,
                shape = RoundedCornerShape(radius.sm),
            )
            .background(
                color = colors.fill.default08,
                shape = RoundedCornerShape(radius.sm),
            )
            .padding(vertical = 9.dp)
            .padding(start = 16.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MoiveBasicTextField(
            state = state,
            textColor = colors.text.default,
            textStyle = typography.body.smNormalR,
            placeholder = placeholder,
            placeholderColor = colors.text.disabled,
            placeholderStyle = typography.body.smNormalR,
            modifier = Modifier.weight(1f),
            keyboardOptions = keyboardOptions,
            onKeyboardAction = {
                focusManager.clearFocus()
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            interactionSource = interactionSource,
            cursorColor = colors.secondary.pressed,
        )

        if (hasValue) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_remove_circle_20),
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
private fun MoiveInputTextFieldPreview() {
    val state = rememberTextFieldState(initialText = "")
    MoiveTheme {
        MoiveInputTextField(
            state = state,
            placeholder = "input_textfield",
            modifier = Modifier.padding(20.dp)
        )
    }
}
