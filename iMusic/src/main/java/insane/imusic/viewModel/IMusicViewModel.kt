package insane.imusic.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import insane.imusic.internet.RetrofitInstance
import insane.imusic.repository.SongRepository
import insane.imusic.repository.UserRepository
import insane.imusic.service.MusicService
import insane.imusic.vo.SongVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class IMusicViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(IMusicState())
    val state = _state.asStateFlow()

    @SuppressLint("StaticFieldLeak")
    private var musicService: MusicService? = null

    private val songRepository = SongRepository()
    private val userRepository = UserRepository()

    /**
     * 获取当前播放位置（毫秒）
     */
    fun getCurrentPosition(): Long {
        return musicService?.getCurrentPosition() ?: 0
    }

    /**
     * 获取歌曲总时长（毫秒）
     */
    fun getDuration(): Long {
        return musicService?.getDuration() ?: 0
    }

    /**
     * 拖拽到指定位置播放
     */
    fun seekTo(position: Long) {
        musicService?.seekTo(position)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            // 设置播放完成监听器
            musicService?.setPlaybackCompletionListener(object :
                MusicService.PlaybackCompletionListener {
                override fun onPlaybackCompleted() {
                    // 在主线程中执行
                    viewModelScope.launch {
                        playNextSong() // 自动播放下一首
                    }
                }
            })
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
        }
    }

    /**
     * 初始化
     */

    init {
        // 同时启动和绑定服务
        Intent(application, MusicService::class.java).also { intent ->
            // 启动前台服务
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                application.startForegroundService(intent)
            } else {
                application.startService(intent)
            }
            // 绑定服务
            application.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }

        viewModelScope.launch {
            _state.value.userVO.value =
                userRepository.userLogin("insane", "7480wozuishuai")
            songRepository.getAllSongs().forEach {
                _state.value.songs.add(it)
            }
        }
    }

    /**
     * 播放音乐
     */
    fun playSong(song: SongVO) {
        viewModelScope.launch {
            _state.value.nowSongVO.value = song
            _state.value.isPlaying.value = true
            try {
                // 通过服务播放音乐
                musicService?.playSong(song)
                // 添加播放次数
                songRepository.addPlayCount(song.id)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value.isPlaying.value = false
            }
        }
    }

    /**
     * 切换播放状态
     */
    fun togglePlayback() {
        musicService?.togglePlayback()
        // 从服务获取当前播放状态
        _state.value.isPlaying.value = musicService?.isPlaying() ?: false
    }

    /**
     * 销毁
     */
    override fun onCleared() {
        super.onCleared()
        getApplication<Application>().unbindService(serviceConnection)
    }

    /**
     * 播放下一首歌曲
     */
    fun playNextSong() {
        val currentSong = _state.value.nowSongVO.value
        if (currentSong != null) {
            val songs = _state.value.songs
            val currentIndex = songs.indexOf(currentSong)

            // 循环播放模式 - 到达末尾时回到第一首
            val nextIndex = (currentIndex + 1) % songs.size
            playSong(songs[nextIndex])
        }
    }

    /**
     * 播放上一首歌曲
     */
    fun playPreviousSong() {
        val currentSong = _state.value.nowSongVO.value
        if (currentSong != null) {
            val songs = _state.value.songs
            val currentIndex = songs.indexOf(currentSong)
            // 循环播放模式 - 到达开头时跳到最后一首
            val previousIndex = if (currentIndex == 0) songs.size - 1 else currentIndex - 1
            playSong(songs[previousIndex])
        }
    }
}

