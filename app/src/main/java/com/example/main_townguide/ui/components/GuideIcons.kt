package com.example.main_townguide.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.main_townguide.ui.theme.TextSecondary

enum class GuideIconType { Back, User, Lock, Eye, Menu, Search, ArrowRight }

@Composable
fun GuideIcon(
    type: GuideIconType,
    modifier: Modifier = Modifier,
    color: Color = TextSecondary
) {
    Canvas(modifier = modifier.size(24.dp)) {
        val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        when (type) {
            GuideIconType.Back -> {
                drawLine(color, Offset(size.width * .65f, size.height * .2f), Offset(size.width * .35f, size.height * .5f), strokeWidth = stroke.width, cap = StrokeCap.Round)
                drawLine(color, Offset(size.width * .35f, size.height * .5f), Offset(size.width * .65f, size.height * .8f), strokeWidth = stroke.width, cap = StrokeCap.Round)
            }
            GuideIconType.User -> {
                drawCircle(color, radius = size.width * .17f, center = Offset(size.width * .5f, size.height * .34f), style = stroke)
                drawArc(
                    color = color,
                    startAngle = 205f,
                    sweepAngle = 130f,
                    useCenter = false,
                    topLeft = Offset(size.width * .24f, size.height * .52f),
                    size = Size(size.width * .52f, size.height * .42f),
                    style = stroke
                )
            }
            GuideIconType.Lock -> {
                drawRoundRect(color, topLeft = Offset(size.width * .25f, size.height * .45f), size = Size(size.width * .5f, size.height * .38f), cornerRadius = androidx.compose.ui.geometry.CornerRadius(5.dp.toPx()), style = stroke)
                drawArc(
                    color = color,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(size.width * .34f, size.height * .18f),
                    size = Size(size.width * .32f, size.height * .4f),
                    style = stroke
                )
            }
            GuideIconType.Eye -> {
                val path = Path().apply {
                    moveTo(size.width * .14f, size.height * .5f)
                    quadraticTo(size.width * .5f, size.height * .2f, size.width * .86f, size.height * .5f)
                    quadraticTo(size.width * .5f, size.height * .8f, size.width * .14f, size.height * .5f)
                }
                drawPath(path, color, style = stroke)
                drawCircle(color, radius = size.width * .09f, center = Offset(size.width * .5f, size.height * .5f), style = stroke)
            }
            GuideIconType.Menu -> {
                drawLine(color, Offset(size.width * .2f, size.height * .32f), Offset(size.width * .8f, size.height * .32f), strokeWidth = stroke.width, cap = StrokeCap.Round)
                drawLine(color, Offset(size.width * .2f, size.height * .5f), Offset(size.width * .8f, size.height * .5f), strokeWidth = stroke.width, cap = StrokeCap.Round)
                drawLine(color, Offset(size.width * .2f, size.height * .68f), Offset(size.width * .8f, size.height * .68f), strokeWidth = stroke.width, cap = StrokeCap.Round)
            }
            GuideIconType.Search -> {
                drawCircle(color, radius = size.width * .22f, center = Offset(size.width * .44f, size.height * .44f), style = stroke)
                drawLine(color, Offset(size.width * .6f, size.height * .6f), Offset(size.width * .82f, size.height * .82f), strokeWidth = stroke.width, cap = StrokeCap.Round)
            }
            GuideIconType.ArrowRight -> {
                drawLine(color, Offset(size.width * .38f, size.height * .25f), Offset(size.width * .62f, size.height * .5f), strokeWidth = stroke.width, cap = StrokeCap.Round)
                drawLine(color, Offset(size.width * .62f, size.height * .5f), Offset(size.width * .38f, size.height * .75f), strokeWidth = stroke.width, cap = StrokeCap.Round)
            }
        }
    }
}
