package com.example.vocabulary_book.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.ActivityMainBinding
import com.example.vocabulary_book.db.AppDatabase
import com.example.vocabulary_book.db.entity.User
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDb = AppDatabase.getDatabase(this)

        // kakao HashKey 확인
        val keyHash = Utility.getKeyHash(this)
        Log.e("log", "keyHash: ${keyHash}")

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo() { tokenInfo, error ->
            // 로그인 기록이 없을 경우
            if (error != null) {
                Log.d("user", "로그인 기록이 없습니다!!")
            // 로그인 기록이 있다면 자동 로그인
            } else if (tokenInfo != null) {
                UserApiClient.instance.me { user, error ->
                    val intent: Intent = Intent(this, VokaMainActivity::class.java)

                    var email = user?.kakaoAccount?.email.toString()
                    var nickname = user?.kakaoAccount?.profile?.nickname


                    Toast.makeText(this, "${nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()

                    // 유저 정보를 VokaMainActivity에 전달
                    intent.putExtra("email", email)
                    intent.putExtra("nickname", nickname)

                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
        }

        // 카카오 로그인 callback
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->

            // 로그인 실패
            if (error != null) {
                Toast.makeText(this, "로그인에 실패했어요", Toast.LENGTH_SHORT).show()

            // 로그인 성공
            } else if (token != null){
                val intent: Intent = Intent(this, VokaMainActivity::class.java)

                UserApiClient.instance.me { user, error ->
                    // 유저 정보 저장
                    var email = user?.kakaoAccount?.email.toString()
                    var nickname = user?.kakaoAccount?.profile?.nickname.toString()
                    var imageUrl = user?.kakaoAccount?.profile?.profileImageUrl.toString()

                    // 유저의 정보가 있다면
                    if (email != null && nickname != null) {
                        GlobalScope.launch {
                            // 유저가 DB에 있는지 확인 여부
                            var userEmailExists = appDb.userDao().isEmailExists(email)

                            // 유저가 DB에 확인되지 않는다면
                            if (userEmailExists == 0) {
                                // 새로운 유저를 DB에 저장
                                var userData = User(null, email, nickname, imageUrl)
                                GlobalScope.launch(Dispatchers.IO) {
                                    appDb.userDao().insert(userData)
                                }
                            }
                        }
                    }
                    // 유저의 정보가 없다면
                    else {
                        Log.d("user", "유저의 정보가 없습니다!!")
                    }

                    Toast.makeText(this, "${nickname}님 어서오세요!", Toast.LENGTH_SHORT).show()

                    // 유저 정보를 VokaMainActivity에 전달
                    intent.putExtra("email", email)
                    intent.putExtra("nickname", nickname)

                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }

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