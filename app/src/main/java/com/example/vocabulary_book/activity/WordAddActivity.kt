package com.example.vocabulary_book.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.WordAddBinding

class WordAddActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = WordAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 단어 추가
        binding.wordAddButton.setOnClickListener() {
            val intent = intent

            setResult(RESULT_OK, intent)
            finish()
        }

        // 단어 추가 취소
        binding.wordAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, VokaAroundActivity::class.java)
            startActivity(intent)
        }
    }
}