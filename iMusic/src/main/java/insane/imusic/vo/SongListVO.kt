package insane.imusic.vo

import java.util.Date

data class SongListVO(
    /**
     * 歌单id
     */
    val id: Long,

    /**
     * 歌单名
     */
    val name: String,

    /**
     * 创建用户id
     */
    val creator: Long,

    /**
     * 创建时间
     */
    val createTime: Date = Date()
)
