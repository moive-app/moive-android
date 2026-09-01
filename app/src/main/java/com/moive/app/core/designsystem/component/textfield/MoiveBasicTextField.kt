package com.moive.app.core.designsystem.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.moive.app.core.designsystem.theme.MoiveTheme


@Composable
fun MoiveBasicTextField(
    state: TextFieldState,
    textColor: Color,
    textStyle: TextStyle,
    placeholder: String,
    placeholderColor: Color,
    placeholderStyle: TextStyle,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    inputTransformation: InputTransformation? = null,
    outputTransformation: OutputTransformation? = null,
    interactionSource: MutableInteractionSource? = null,
    cursorColor: Color = textColor,
    suffix: (@Composable () -> Unit)? = null,
) {

    BasicTextField(
        state = state,
        modifier = modifier,
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = textStyle.copy(color = textColor),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        inputTransformation = inputTransformation,
        outputTransformation = outputTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(cursorColor),
        decorator = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderColor,
                            style = placeholderStyle,
                        )
                    }
                    innerTextField()
                }
                suffix?.invoke()
            }
        },
    )
}

@Preview
@Composable
private fun MoiveBasicTextFieldPreview() {
    val state = rememberTextFieldState(initialText = "")

    MoiveTheme {
        MoiveBasicTextField(
            state = state,
            textColor = MoiveTheme.colors.gray800,
            textStyle = MoiveTheme.typography.body.smNormalR,
            placeholder = "장소를 입력하세요",
            placeholderColor = MoiveTheme.colors.gray300,
            placeholderStyle = MoiveTheme.typography.body.smNormalR,
        )
    }

}
