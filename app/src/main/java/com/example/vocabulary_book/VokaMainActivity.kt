package com.example.vocabulary_book

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.VokaMainBinding
import com.kakao.sdk.user.UserApiClient

class VokaMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokaMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityLauncher = openActivityResultLauncher()

        // 카카오 로그아웃
        binding.kakaoLogoutButton.setOnClickListener() {

            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Toast.makeText(this, "로그아웃에 실패했어요 $error", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "안녕히가세요!", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            }
        }

        // 단어장 추가 이동
        binding.vokaAddMoveButton.setOnClickListener() {
            val intent: Intent = Intent(this, VokaFileAddActivity::class.java)
            activityLauncher.launch(intent)
        }

        // 단어장 이동
        binding.vokaFile1.setOnClickListener() {
            val intent: Intent = Intent(this, VokaAroundActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openActivityResultLauncher(): ActivityResultLauncher<Intent> {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "단어장 추가 성공", Toast.LENGTH_SHORT).show()
                val data1 = result.data?.getStringExtra("data1")
                Log.d("log", "$data1")
            }
            else {
                Toast.makeText(this, "단어장 추가 실패", Toast.LENGTH_SHORT).show()
            }
        }
        return resultLauncher
    }
}