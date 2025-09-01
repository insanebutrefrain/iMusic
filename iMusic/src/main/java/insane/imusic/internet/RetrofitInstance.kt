package insane.imusic.internet

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit实例
 */
object RetrofitInstance {
    private const val BASE_URL = "http://192.168.137.1:8080/"

    val client = OkHttpClient.Builder()
        .connectTimeout(2500, TimeUnit.MILLISECONDS)  // 连接超时时间
        .readTimeout(2500, TimeUnit.MILLISECONDS)     // 读取超时时间
        .writeTimeout(2500, TimeUnit.MILLISECONDS)    // 写入超时时间
        .addInterceptor(HeaderInterceptor())
        .build()


    val retrofit: Retrofit by lazy {
        Retrofit.Builder().client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val songAPI: SongAPI by lazy {
        retrofit.create(SongAPI::class.java)
    }

    val userApi: UserAPI by lazy {
        retrofit.create(UserAPI::class.java)
    }
}
