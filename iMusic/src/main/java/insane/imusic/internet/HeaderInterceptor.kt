package insane.imusic.internet

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器，添加请求头
 */
class HeaderInterceptor() : Interceptor {
    private var account = "insane"
    private var password = "7480wozuishuai"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("account", account)
            .addHeader("password", password)
            .build()
        return chain.proceed(request)
    }

    fun setAccountAndPassword(account: String, password: String) {
        this.account = account
        this.password = password
    }
}