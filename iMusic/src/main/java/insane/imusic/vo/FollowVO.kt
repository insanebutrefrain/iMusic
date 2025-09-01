package insane.imusic.vo;


import java.util.Date;

data class FollowVO(
    /**
     * 关注id
     */
    val id: Long,

    /**
     * 关注者用户id
     */
    val follower: Long,

    /**
     * 被关注者用户id
     */
    val following: Long,

    /**
     * 关注时间
     */
    val time: Date = Date()
)
