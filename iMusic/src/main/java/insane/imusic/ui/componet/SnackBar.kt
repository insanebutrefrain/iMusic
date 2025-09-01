package insane.imusic.ui.componet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import insane.imusic.R
import insane.imusic.viewModel.IMusicViewModel

/**
 * SnackBar
 * 音乐播放横条
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SnackBar(navController: NavHostController, viewModel: IMusicViewModel) {
    val nowPlaying by viewModel.state.value.nowSongVO
    val isPlaying by viewModel.state.value.isPlaying
    Row(
        modifier = Modifier
            .clickable {
                // 跳转到正在播放
                navController.navigate("playing") {
                }
            }
            .fillMaxWidth(fraction = 0.9f)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RotateIcon(
            painter = painterResource(R.drawable.image_playing),
            isPlaying = isPlaying
        )
        Text(
            text = nowPlaying?.name ?: "未选择歌曲",
            modifier = Modifier
                .wrapContentHeight()
                .weight(1.0f)
        )
        IconButton(
            onClick = {
                viewModel.togglePlayback()
            }) {
            Icon(
                painter = if (isPlaying) {
                    painterResource(R.drawable.icon_pause)
                } else {
                    painterResource(R.drawable.icon_play_arrow)
                },
                contentDescription = "播放或暂停"
            )
        }

        IconButton(onClick = {
            // todo 播放列表
        }) {
            Icon(
                painter = painterResource(R.drawable.icon_play_list),
                contentDescription = "播放列表"
            )
        }
    }
}
