package insane.imusic.vo

import java.util.Date

data class SongVO(
    /**
     * 音乐id
     */
    val id: Long,

    /**
     * 歌名
     */
    val name: String,

    /**
     * 歌手
     */
    val singer: String?,

    /**
     * 上传时间
     */
    val uploadTime: Date = Date(),

    /**
     * 歌曲存放路径
     */
    val path: String,

    /**
     * 歌词
     */
    val lyrics: String?,

    /**
     * 歌曲上传用户id
     */
    val songUploader: Long,

    /**
     * 歌词上传用户id
     */
    val lyricsUploader: Long?,

    /**
     * 播放次数
     */
    val frequency: Int = 0,
)
