package com.example.vocabulary_book

import android.app.Application
import android.content.Context
import com.kakao.sdk.common.KakaoSdk

class CapstoneApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance : CapstoneApplication? = null
        fun context() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        /** KakoSDK init */
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}