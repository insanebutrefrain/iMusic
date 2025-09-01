package insane.imusic.vo;

import java.util.Date;

data class SongInListVO(
    /**
     * 主键id
     */
    val id: Long,

    /**
     * 歌单id
     */
    val songListId: Long,

    /**
     * 歌曲id
     */
    val songId: Long,

    /**
     * 添加时间
     */
    val addTime: Date = Date()
)
