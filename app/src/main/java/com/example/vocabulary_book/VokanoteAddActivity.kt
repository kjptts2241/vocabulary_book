package com.example.vocabulary_book

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.VokanoteAddBinding

class VokanoteAddActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokanoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 단어장 추가
        binding.vokanoteAddButton.setOnClickListener() {
            val intent = intent

            setResult(RESULT_OK, intent)
            finish()
        }

        // 단어장 추가 취소
        binding.vokanoteAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, VokaMainActivity::class.java)
            startActivity(intent)
        }
    }
}