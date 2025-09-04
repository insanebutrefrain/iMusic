package insane.imusic.repository


import insane.imusic.internet.RetrofitInstance
import insane.imusic.vo.UserVO

class UserRepository {
    /**
     * 用户注册
     */
    suspend fun addUser(userVO: UserVO): UserVO? {
        val response = RetrofitInstance.userApi.addUser(userVO)
        if (response.code == 0) {
            return response.data
        }
        return null
    }

    /**
     * 更新用户信息
     */
    suspend fun updateUser(userVO: UserVO): Boolean {
        val response = RetrofitInstance.userApi.updateUser(userVO)
        if (response.code == 0) {
            return response.data ?: false
        }
        return false
    }

    /**
     * 删除用户
     */
    suspend fun deleteUser(id: Long): Boolean {
        val response = RetrofitInstance.userApi.deleteUser(id)
        if (response.code == 0) {
            return response.data ?: false
        }
        return false
    }

    /**
     * 检查账号是否存在
     */
    suspend fun checkAccountExists(account: String): Boolean {
        val response = RetrofitInstance.userApi.checkAccountExists(account)
        if (response.code == 0) {
            return response.data ?: false
        }
        return false
    }

    /**
     * 用户登录
     */
    suspend fun userLogin(account: String, password: String): UserVO? {
        val response = RetrofitInstance.userApi.userLogin(account, password)
        if (response.code == 0) {
            return response.data
        }
        return null
    }
}
