package com.example.vocabulary_book

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.DangoAddBinding

class DangoAddActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DangoAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 단어 추가
        binding.dangoAddButton.setOnClickListener() {
            val intent = intent

            setResult(RESULT_OK, intent)
            finish()
        }

        // 단어 추가 취소
        binding.dangoAddCancelButton.setOnClickListener() {
            val intent: Intent = Intent(this, VokaAroundActivity::class.java)
            startActivity(intent)
        }
    }
}