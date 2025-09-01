package insane.imusic.vo;

import java.util.Date;


data class MessageVO(
    /**
     * 消息id
     */
    val id: Long,

    /**
     * 发送者用户id
     */
    val sender: Long,

    /**
     * 接收方用户id
     */
    val receiver: Long,

    /**
     * 消息类型
     */
    val type: String,

    /**
     * 消息内容
     */
    val content: String,

    /**
     * 发送时间
     */
    val time: Date = Date()
)
