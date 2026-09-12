package com.moive.app.core.designsystem.component.toast

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoiveSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    ) { data ->
        val visuals = data.visuals as MoiveToastVisuals

        MoiveToast(
            text = visuals.message,
            type = visuals.type,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 80.dp),
        )
    }
}
