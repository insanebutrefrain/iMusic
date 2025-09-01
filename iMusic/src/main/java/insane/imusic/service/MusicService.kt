package insane.imusic.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import insane.imusic.MainActivity
import insane.imusic.R
import insane.imusic.internet.RetrofitInstance
import insane.imusic.vo.SongVO

class MusicService : MediaSessionService() {
    private var mediaSession: MediaSession? = null
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var notificationManager: NotificationManager

    companion object {
        const val CHANNEL_ID = "music_playback_channel"
        const val NOTIFICATION_ID = 1
    }

    // 添加 Binder 类
    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    private val binder = MusicBinder()

    override fun onBind(intent: Intent?): IBinder? {
        super.onBind(intent)
        return binder
    }

    // 添加 onStartCommand 以支持前台服务
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        showNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()

        // 初始化 ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()

        // 创建 MediaSession
        mediaSession = MediaSession.Builder(this, exoPlayer)
            .setCallback(mediaSessionCallback)
            .build()

        // 初始化通知管理器
        notificationManager = getSystemService(NotificationManager::class.java)
        createNotificationChannel()
        // 延迟显示通知直到服务完全初始化
        // 使用 Handler 确保在主线程中执行
        Handler(Looper.getMainLooper()).post {
            showNotification()
        }
    }

    fun playSong(song: SongVO) {
        try {
            // 构建完整的歌曲 URL 并使用 ExoPlayer 播放
            val baseUrl = RetrofitInstance.retrofit.baseUrl().toString()
            val songUrl = "${baseUrl}song/get?path=${song.path}"
            val mediaItem = MediaItem.fromUri(songUrl)

            // 使用 ExoPlayer 播放
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            // 更新通知
            showNotification()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun togglePlayback() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
        // 更新通知状态
        showNotification()
    }

    fun isPlaying(): Boolean = exoPlayer.isPlaying

    private val mediaSessionCallback = object : MediaSession.Callback {
        // 可以在这里处理自定义的媒体会话回调
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "音乐播放",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "音乐播放通知"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification() {
        try {
            val notification = createNotification()
            startForeground(NOTIFICATION_ID, notification)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("iMusic")
            .setContentText("正在播放音乐")
            .setSmallIcon(R.drawable.icon)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setAutoCancel(false)
            .build()
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}

