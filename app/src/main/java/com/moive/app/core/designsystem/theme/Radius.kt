package com.moive.app.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Sm = 8.dp
val Md = 12.dp
val Lg = 16.dp
val Xl = 20.dp
val Xxl = 24.dp
val Xxxl = 28.dp
val Circular = 999.dp

@Immutable
data class MoiveRadius(
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp,
    val xxxl: Dp,
    val circular: Dp,
)

val defaultMoiveRadius = MoiveRadius(
    sm = Sm,
    md = Md,
    lg = Lg,
    xl = Xl,
    xxl = Xxl,
    xxxl = Xxxl,
    circular = Circular,
)

@Preview
@Composable
private fun MoiveRadiusPreview() {
    MoiveTheme {
        Column(modifier = Modifier.padding(8.dp)) {
            listOf(
                "sm" to MoiveTheme.radius.sm,
                "md" to MoiveTheme.radius.md,
                "lg" to MoiveTheme.radius.lg,
                "xl" to MoiveTheme.radius.xl,
                "xxl" to MoiveTheme.radius.xxl,
                "xxxl" to MoiveTheme.radius.xxxl,
                "circular" to MoiveTheme.radius.circular,
            ).chunked(4).forEach { rowItems ->
                Row {
                    rowItems.forEach { (label, radius) ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .background(
                                        color = MoiveTheme.colors.primary.sub01,
                                        shape = RoundedCornerShape(radius),
                                    ),
                            )
                            Text(text = label)
                        }
                    }
                }
            }
        }
    }
}
