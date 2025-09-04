package insane.imusic.repository

import insane.imusic.internet.RetrofitInstance
import insane.imusic.vo.SongVO
import okhttp3.ResponseBody

class SongRepository {
    suspend fun getAllSongs(): List<SongVO> {
        val response = RetrofitInstance.songApi.getAllSongs()
        if (response.code == 0) {
            return response.data!!
        }
        return emptyList()

    }

    suspend fun addPlayCount(id: Long): Boolean {
        val response = RetrofitInstance.songApi.addPlayCount(id)
        if (response.code != 0) {
            return response.data!!
        }
        return false
    }
}