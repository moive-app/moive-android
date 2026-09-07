package com.moive.app.core.designsystem.component.tooltip

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moive.app.core.designsystem.theme.MoiveTheme
import com.moive.app.core.designsystem.theme.MoiveTheme.colors
import com.moive.app.core.designsystem.theme.MoiveTheme.radius
import com.moive.app.core.designsystem.theme.MoiveTheme.typography

private val TailWidth = 13.dp
private val TailHeight = 10.dp
private val TailTipRadius = 4.dp

@Composable
fun MoiveToolTip(
    text: String,
    modifier: Modifier = Modifier,
) {
    val bubbleColor = colors.fill.default01
    val cornerRadius = radius.sm

    Column(
        modifier = modifier.drawBehind {
            val tailHeightPx = TailHeight.toPx()
            val tailHalfWidthPx = (TailWidth / 2).toPx()
            val bubbleHeight = size.height - tailHeightPx
            val centerX = size.width / 2f

            val bubblePath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(0f, 0f, size.width, bubbleHeight),
                        cornerRadius = CornerRadius(cornerRadius.toPx()),
                    ),
                )
            }
            val leftBase = Offset(centerX - tailHalfWidthPx, bubbleHeight)
            val rightBase = Offset(centerX + tailHalfWidthPx, bubbleHeight)
            val tip = Offset(centerX, size.height)

            val tipRadiusPx = TailTipRadius.toPx()
                .coerceAtMost((rightBase - tip).getDistance())
                .coerceAtMost((leftBase - tip).getDistance())
            val fromRight = tip + (rightBase - tip).let { it / it.getDistance() * tipRadiusPx }
            val fromLeft = tip + (leftBase - tip).let { it / it.getDistance() * tipRadiusPx }

            val tailPath = Path().apply {
                moveTo(leftBase.x, leftBase.y)
                lineTo(rightBase.x, rightBase.y)
                lineTo(fromRight.x, fromRight.y)
                quadraticTo(tip.x, tip.y, fromLeft.x, fromLeft.y)
                close()
            }

            drawPath(
                path = Path().apply { op(bubblePath, tailPath, PathOperation.Union) },
                color = bubbleColor,
            )
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = text,
            color = colors.text.onBg,
            style = typography.body.smNormalR,
            modifier = Modifier.padding(8.dp),
        )
        Spacer(modifier = Modifier.size(width = TailWidth, height = TailHeight))
    }
}

@Preview(showBackground = true)
@Composable
private fun MoiveToolTipPreview() {
    MoiveTheme {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MoiveToolTip(
                text = "아직 조건 입력 중이에요!",
            )
            Spacer(modifier = Modifier.size(20.dp))
            MoiveToolTip(
                text = "모임이 확정됐어요. 모임 정보를 확인해보세요!",
            )
        }
    }
}
