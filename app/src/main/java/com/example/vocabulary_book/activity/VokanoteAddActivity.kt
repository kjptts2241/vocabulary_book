package com.example.vocabulary_book.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.R
import com.example.vocabulary_book.databinding.VokanoteAddBinding

class VokanoteAddActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokanoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 단어장 추가
        binding.vokanoteAddButton.setOnClickListener() {
            val intent = intent

            var language = ""
            var title = binding.vokanoteTitle.toString()

            binding.languageGroup.setOnCheckedChangeListener{ group, checkedId ->
                when(checkedId) {
                    R.id.english_button -> language = "English"
                    R.id.japan_button -> language = "日本語"
                    R.id.china_button -> language = "中国語"
                }
            }

            Toast.makeText(this, "${language}", Toast.LENGTH_SHORT).show()

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