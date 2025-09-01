package insane.imusic.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import insane.imusic.vo.SongVO
import insane.imusic.vo.UserVO

data class IMusicState(
    var userVO: MutableState<UserVO?> = mutableStateOf(null),
    var nowSongVO: MutableState<SongVO?> = mutableStateOf(null),
    var isPlaying: MutableState<Boolean> = mutableStateOf(false),
    var songs: SnapshotStateList<SongVO> = mutableStateListOf()
)