package com.example.vocabulary_book

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class CapstoneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        /** KakoSDK init */
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}