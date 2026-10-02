package com.moive.app

import android.app.Application
import com.amplitude.android.Amplitude
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk
import com.moive.app.core.fcm.NotificationChannels
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MoiveApplication : Application() {

    @Inject
    lateinit var amplitude: Amplitude

    override fun onCreate() {
        super.onCreate()

        initTimber()
        NotificationChannels.createDefaultChannel(this)
        initKakaoSdk()
        initKakaoMapSdk()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKakaoSdk() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }

    private fun initKakaoMapSdk() {
        KakaoMapSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}
