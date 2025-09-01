package insane.imusic.ui.componet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * 旋转图片
 */
@Composable
fun RotateIcon(
    painter: Painter,
    isPlaying: Boolean,
    modifier: Modifier = Modifier.size(38.dp),
    rotationSpeed: Float = 0.5f,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Gray
) {
    var rotation by remember { mutableFloatStateOf(0f) }

    // 动画控制
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (true) {
                rotation += rotationSpeed
                if (rotation >= 360f) rotation = 0f
                delay(20)
            }
        }
    }

    Box(
        modifier = modifier
            .shadow(8.dp, CircleShape)
            .border(borderWidth, borderColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "旋转图片",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .rotate(rotation)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
    }
}