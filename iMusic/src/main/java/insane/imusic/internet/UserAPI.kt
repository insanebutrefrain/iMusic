package insane.imusic.internet

import insane.imusic.vo.UserVO
import retrofit2.http.*

interface UserAPI {
    
    /**
     * 用户注册
     * @param userAddRequest 用户注册信息
     */
    @POST("/user/register")
    suspend fun addUser(@Body userVO: UserVO): BaseResponse<UserVO>
    
    /**
     * 更新用户信息
     * @param userVO 用户信息
     */
    @POST("/user/update")
    suspend fun updateUser(@Body userVO: UserVO): BaseResponse<Boolean>
    
    /**
     * 删除用户
     * @param id 用户ID
     */
    @FormUrlEncoded
    @POST("/user/delete")
    suspend fun deleteUser(@Field("id") id: Long): BaseResponse<Boolean>
    
    /**
     * 检查账号是否存在
     * @param account 账号
     */
    @GET("/user/exists")
    suspend fun checkAccountExists(@Query("account") account: String): BaseResponse<Boolean>
    
    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun userLogin(
        @Field("account") account: String,
        @Field("password") password: String
    ): BaseResponse<UserVO>
}
