package com.example.vocabulary_book

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.VokaFileAddBinding

class VokaFileAddActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokaFileAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 단어장 추가
        binding.vokaAddButton.setOnClickListener() {
            val intent = intent

            setResult(RESULT_OK, intent)
            finish()
        }

        // 단어장 추가 취소
        binding.vokaAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, VokaMainActivity::class.java)
            startActivity(intent)
        }
    }
}