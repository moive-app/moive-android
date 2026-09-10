package com.moive.app.core.network

import com.moive.app.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class KakaoAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .header(AUTHORIZATION, "$KAKAO_AK_PREFIX ${BuildConfig.KAKAO_REST_API_KEY}")
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val KAKAO_AK_PREFIX = "KakaoAK"
    }
}
