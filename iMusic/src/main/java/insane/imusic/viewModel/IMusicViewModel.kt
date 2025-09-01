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

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
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
                RetrofitInstance.userApi.userLogin("insane", "7480wozuishuai").data
            RetrofitInstance.songAPI.getAllSongs().data?.forEach {
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
}

