package com.example.vocabulary_book.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.R
import com.example.vocabulary_book.databinding.VokanoteAddBinding
import com.example.vocabulary_book.db.AppDatabase

class VokanoteAddActivity: AppCompatActivity() {

    private var appDb: AppDatabase? = null

    private var email: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokanoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDb = AppDatabase.getDatabase(this)

        var language = ""
        var title = ""

        // 이메일로 유저의 id 가져오기
//        email = intent.getStringExtra("email")
//        var userId = appDb!!.userDao().isEmailToUserId(email!!)
//        Log.d("userId", userId.toString())

        // 언어 선택 저장
        binding.languageGroup.setOnCheckedChangeListener{ group, checkedId ->
            Log.d("asd", "radio 버튼 클릭")
            when(checkedId) {
                R.id.english_button -> language = "English"
                R.id.japan_button -> language = "日本語"
                R.id.china_button -> language = "中国語"
            }
        }

        // 단어장 추가
        binding.vokanoteAddButton.setOnClickListener() {

            // 제목 저장
            title = binding.vokanoteTitle.text.toString()

            // 언어와 제목이 비지 않았다면 단어장 저장
//            if (title != null && language != null) {
//
//                var vokanoteData = Vokanote(null, userId, language, title)
//                GlobalScope.launch(Dispatchers.IO) {
//                    appDb.vokanoteDao().insert(vokanoteData)
//                }
//            }

//
//                intent.putExtra("title", title)
//            }
//            else {
//                Toast.makeText(this, "단어장 추가 실패", Toast.LENGTH_SHORT).show()
//            }

            setResult(RESULT_OK, intent)
            finish()
        }

        // 단어장 추가 취소
        binding.vokanoteAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, FragmentMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}