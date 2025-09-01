package insane.imusic.vo;


import java.util.Date;

data class CollectionVO(
    /**
     * 收藏id
     */
    val id: Long,

    /**
     * 歌单id
     */
    val songListId: Long,

    /**
     * 用户id
     */
    val userid: Long,

    /**
     * 收藏时间
     */
    val time: Date = Date(),
)
