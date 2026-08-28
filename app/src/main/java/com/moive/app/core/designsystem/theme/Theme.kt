package com.moive.app.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalMoiveColors = staticCompositionLocalOf<MoiveColors> {
    error("No MoiveColors provided")
}
private val LocalMoiveTypography = staticCompositionLocalOf<MoiveTypography> {
    error("No MoiveTypography provided")
}

object MoiveTheme {
    val colors: MoiveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMoiveColors.current

    val typography: MoiveTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMoiveTypography.current
}

@Composable
fun ProvideMoiveColorsAndTypography(
    colors: MoiveColors,
    typography: MoiveTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMoiveColors provides colors,
        LocalMoiveTypography provides typography,
        content = content,
    )
}

@Composable
fun MoiveTheme(
    content: @Composable () -> Unit
) {
    ProvideMoiveColorsAndTypography(
        colors = defaultMoiveColors,
        typography = defaultMoiveTypography,
    ) {
        MaterialTheme(
            content = content
        )
    }
}
