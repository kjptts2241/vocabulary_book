package com.example.vocabulary_book.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vocabulary_book.HomeFragment
import com.example.vocabulary_book.InfoFragment
import com.example.vocabulary_book.ProfileFragment
import com.example.vocabulary_book.R
import com.example.vocabulary_book.databinding.FragmentMainBinding

class FragmentMainActivity : AppCompatActivity() {

    private var email: String? = null
    private var nickname: String? = null
    private var imageUrl: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 유저 정보 저장
        email = intent.getStringExtra("email")
        nickname = intent.getStringExtra("nickname")
        imageUrl = intent.getStringExtra("imageUrl")

        // home 화면 초기 설정
        setDataFragment(HomeFragment(), email, nickname, imageUrl)

        // 프래그먼트 화면 전환
        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when(item.itemId) {

                R.id.home -> setDataFragment(HomeFragment(), email, nickname, imageUrl)
                R.id.profile -> setDataFragment(ProfileFragment(), email, nickname, imageUrl)
                R.id.info -> replaceFragment(InfoFragment())

            }

            true
        }
    }

    // 프래그먼트에 데이터 전달
    private fun setDataFragment(fragment : Fragment, email: String?, nickname: String?, imageUrl: String?) {
        val bundle = Bundle()
        bundle.putString("email", email)
        bundle.putString("nickname", nickname)
        bundle.putString("imageUrl", imageUrl)

        fragment.arguments = bundle
        replaceFragment(fragment)
    }
    
    // 프래그먼트 화면 전환 함수
    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}