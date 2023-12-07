package com.example.vocabulary_book.activity

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

        /** kakao HashKey확인 */
        val keyHash = Utility.getKeyHash(this)
        Log.e("log", "keyHash: ${keyHash}")

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo() { tokenInfo, error ->
            // 로그인 기록이 없을 경우
            if (error != null) {
                //TODO: 로그인 기록 없음

            // 로그인 기록이 있다면 자동 로그인
            } else if (tokenInfo != null) {
                UserApiClient.instance.me { user, error ->
                    val nickname = user?.kakaoAccount?.profile?.nickname

                    Toast.makeText(this, "${nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()
                }
                val intent: Intent = Intent(this, VokaMainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            // 로그인 실패
            if (error != null) {
                Toast.makeText(this, "로그인에 실패했어요", Toast.LENGTH_SHORT).show()

            // 로그인 성공
            } else if (token != null){
                UserApiClient.instance.me { user, error ->
                    val email = user?.kakaoAccount?.email
                    val nickname = user?.kakaoAccount?.profile?.nickname
                    val profileImageUrl = user?.kakaoAccount?.profile?.profileImageUrl

                    Toast.makeText(this, "${nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()

                    // 유저 DB 확인

//                    val userEmailExists = AppDatabase.getInstance(this)?.userDao()?.isEmailExists(email)
                    // 유저가 DB에 확인되지 않는 경우
//                    if (userEmailExists == 0) {
//                        // DB에 유저 저장
//                          val userData = User("", "", "")
//                        AppDatabase.getInstance(this)?.userDao()?.insert(userData)
//
//                    // 유저가 DB에 확인되는 경우
//                    } else {
//                        //TODO: 유저가 DB에 저장되있음
//                    }
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