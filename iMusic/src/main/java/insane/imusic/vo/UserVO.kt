package insane.imusic.vo

import java.util.Date

data class UserVO(
    /**
     * 用户id
     */
    val id: Long,

    /**
     * 账号
     */
    val account: String,

    /**
     * 密码
     */
    val password: String,

    /**
     * 用户名
     */
    val name: String,

    /**
     * 性别
     */
    val gender: String,

    /**
     * 个性签名
     */
    val signs: String,

    /**
     * 头像地址
     */
    val iconPath: String,

    /**
     * 音乐标签
     */
    val label: String,

    /**
     * 注册时间
     */
    val createTime: Date = Date(),

    /**
     * 粉丝数
     */
    val fans: Int = 0,

    /**
     * 关注数
     */
    val follows: Int = 0,

    /**
     * 听歌时长(分钟)
     */
    val listenTime: Int = 0
)
