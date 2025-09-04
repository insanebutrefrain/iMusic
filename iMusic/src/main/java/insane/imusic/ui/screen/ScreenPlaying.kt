package insane.imusic.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import insane.imusic.R
import insane.imusic.ui.componet.RotateIcon
import insane.imusic.viewModel.IMusicViewModel
import kotlinx.coroutines.delay

@SuppressLint("StateFlowValueCalledInComposition", "NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayingScreen(viewModel: IMusicViewModel, navController: NavHostController) {
    val song = viewModel.state.value.nowSongVO
    val isPlaying = viewModel.state.value.isPlaying
    val background = MaterialTheme.colorScheme.surfaceVariant
    val onBackGround = MaterialTheme.colorScheme.onSurfaceVariant
    // 添加一个用于更新进度条的状态
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    // 使用协程定期更新进度
    LaunchedEffect(Unit) {
        while (true) {
            currentPosition = viewModel.getCurrentPosition()
            duration = viewModel.getDuration()
            delay(1000) // 每秒更新一次
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = background),
                title = { Text(text = "正在播放", color = onBackGround) },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "分享"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "返回"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 黑胶唱片
            RotateIcon(
                painterResource(id = R.drawable.image_playing),
                isPlaying = isPlaying.value,
                modifier = Modifier.size(300.dp),
            )
            Spacer(modifier = Modifier.height(50.dp))
            // 歌曲信息
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = song.value?.name ?: "未播放",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = onBackGround
                )
                Text(
                    text = song.value?.singer ?: "未知歌手",
                    fontSize = 16.sp,
                    color = onBackGround
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 进度条
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                SongProgressSlider(
                    currentPosition = currentPosition,
                    duration = duration,
                    onSeek = { position ->
                        viewModel.seekTo(position)
                    }
                )
            }

            // 控制按钮
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_refresh),
                        contentDescription = "循环"
                    )
                }
                IconButton(onClick = {
                    viewModel.playPreviousSong()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_last_song),
                        contentDescription = "上一曲"
                    )
                }
                IconButton(onClick = {
                    viewModel.togglePlayback()
                }) {
                    Icon(
                        painter = painterResource(id = if (isPlaying.value) R.drawable.icon_play_arrow else R.drawable.icon_pause),
                        contentDescription = "暂停"
                    )
                }
                IconButton(onClick = {
                    viewModel.playNextSong()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_next_song),
                        contentDescription = "下一曲"
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_play_list),
                        contentDescription = "播放列表"
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

/**
 * 歌曲进度条
 */
@Composable
fun SongProgressSlider(
    currentPosition: Long,
    duration: Long,
    onSeek: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0f) }
    var isSeeking by remember { mutableStateOf(false) }

    // 当播放位置更新时更新进度条，但不覆盖用户正在拖动时的值
    LaunchedEffect(currentPosition, duration) {
        if (!isSeeking) {
            progress = if (duration > 0) currentPosition.toFloat() / duration.toFloat() else 0f
        }
    }
    Column(modifier = modifier) {
        Slider(
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondary
            ),
            value = progress,
            onValueChange = { newValue ->
                isSeeking = true
                progress = newValue
            },
            onValueChangeFinished = {
                isSeeking = false
                onSeek((progress * duration).toLong())
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime((progress * duration).toLong()),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = formatTime(duration),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


/**
 * 时间格式化
 */
@SuppressLint("DefaultLocale")
fun formatTime(milliseconds: Long): String {
    val seconds = (milliseconds / 1000).toInt()
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}


