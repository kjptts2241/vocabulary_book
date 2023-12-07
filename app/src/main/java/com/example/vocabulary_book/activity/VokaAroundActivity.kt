package com.example.vocabulary_book.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.vocabulary_book.databinding.VokanoteAroundBinding

class VokaAroundActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = VokanoteAroundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityLauncher = openActivityResultLauncher()

        // 단어 추가 이동
        binding.wordAddMoveButton.setOnClickListener() {
            val intent: Intent = Intent(this, WordAddActivity::class.java)
            activityLauncher.launch(intent)
        }
    }

    private fun openActivityResultLauncher(): ActivityResultLauncher<Intent> {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "단어 등록 성공", Toast.LENGTH_SHORT).show()
                val data1 = result.data?.getStringExtra("data1")
                Log.d("log", "$data1")
            }
            else {
                Toast.makeText(this, "단어 등록 실패", Toast.LENGTH_SHORT).show()
            }
        }
        return resultLauncher
    }
}