package insane.imusic.internet

import java.io.Serializable

/**
 * 通用返回类
 *
 * @param <T>
 */
data class BaseResponse<T>(
    val code: Int?,
    val data: T?,
    val message: String?
) : Serializable

