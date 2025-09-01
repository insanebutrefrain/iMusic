package insane.imusic.internet

import androidx.media3.common.MediaItem
import insane.imusic.vo.SongVO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface SongAPI {
    /**
     * 获取所有歌曲列表
     */
    @GET("/song/list")
    suspend fun getAllSongs(): BaseResponse<List<SongVO>>

    /**
     * 获取歌曲文件
     * @param path 歌曲文件路径
     */
    @GET("/song/get")
    @Streaming
    suspend fun getSongStream(@Query("path") path: String): Response<ResponseBody>

}
