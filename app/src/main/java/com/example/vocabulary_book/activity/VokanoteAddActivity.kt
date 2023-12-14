package com.example.vocabulary_book.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.R
import com.example.vocabulary_book.databinding.VokanoteAddBinding
import com.example.vocabulary_book.db.AppDatabase
import com.example.vocabulary_book.db.entity.Vokanote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VokanoteAddActivity: AppCompatActivity() {

    private var email: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokanoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appDb = AppDatabase.getDatabase(this)

        var language = ""
        var title = ""

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

            // 값을 정상적으로 입력했거나 단어장 횟수가 5개 밑이라면 단어장 저장
            if (binding.vokanoteTitle.text.isNotEmpty() && language.isNotEmpty()) {

                // 단어장 저장
                val vokanoteData = Vokanote(language, title)
                GlobalScope.launch(Dispatchers.IO) {
                    appDb.vokanoteDao().insertVokanote(vokanoteData)
                }

                intent.putExtra("title", title)

                setResult(RESULT_OK, intent)
                finish()
            }
            else {
                Toast.makeText(this, "값을 입력해주세요!!", Toast.LENGTH_SHORT).show()
            }
        }

        // 단어장 추가 취소
        binding.vokanoteAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, FragmentMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}