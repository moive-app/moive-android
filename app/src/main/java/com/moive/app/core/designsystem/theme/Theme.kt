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
private val LocalMoiveRadius = staticCompositionLocalOf<MoiveRadius> {
    error("No MoiveRadius provided")
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

    val radius: MoiveRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalMoiveRadius.current
}

@Composable
fun ProvideMoiveDesignTokens(
    colors: MoiveColors,
    typography: MoiveTypography,
    radius: MoiveRadius,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalMoiveColors provides colors,
        LocalMoiveTypography provides typography,
        LocalMoiveRadius provides radius,
        content = content,
    )
}

@Composable
fun MoiveTheme(
    content: @Composable () -> Unit
) {
    ProvideMoiveDesignTokens(
        colors = defaultMoiveColors,
        typography = defaultMoiveTypography,
        radius = defaultMoiveRadius,
    ) {
        MaterialTheme(
            content = content
        )
    }
}
