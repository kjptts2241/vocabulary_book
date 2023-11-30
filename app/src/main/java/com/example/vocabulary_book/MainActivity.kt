package com.example.vocabulary_book

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** HashKey확인 */
        val keyHash = Utility.getKeyHash(this)
        Log.e("log", "keyHash: ${keyHash}")

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo() { tokenInfo, error ->
            if (error != null) {
                //TODO: 로그인 기록 없음
            } else if (tokenInfo != null) {
                UserApiClient.instance.me { user, error ->
                    Toast.makeText(this, "${user?.kakaoAccount?.profile?.nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()
                }
                val intent: Intent = Intent(this, VokaMainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                // 로그인 실패
                Toast.makeText(this, "로그인에 실패했어요", Toast.LENGTH_SHORT).show()
            }
            else if (token != null){
                // 로그인 성공
                UserApiClient.instance.me { user, error ->
                    Toast.makeText(this, "${user?.kakaoAccount?.profile?.nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()
                }
                val intent: Intent = Intent(this, VokaMainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        // 카카오 로그인
        binding.kakaoLoginButton.setOnClickListener() {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
            else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}