package com.example.vocabulary_book

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.vocabulary_book.activity.MainActivity
import com.example.vocabulary_book.activity.VokaAroundActivity
import com.example.vocabulary_book.activity.VokanoteAddActivity
import com.example.vocabulary_book.databinding.FragmentHomeBinding
import com.kakao.sdk.user.UserApiClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var email: String? = null
    private var nickname: String? = null
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            email = it.getString("email")
            nickname = it.getString("nickname")
            imageUrl = it.getString("imageUrl")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val activityLauncher = openActivityResultLauncher()

        // 카카오 로그아웃
        binding.kakaoLogoutButton.setOnClickListener() {
            arguments?.let {
                UserApiClient.instance.logout { error ->
                    // 로그아웃 실패
                    if (error != null) {
                        Toast.makeText(
                            context,
                            "${nickname}님 로그아웃에 실패했어요 $error",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        // 로그아웃 성공
                    } else {
                        Toast.makeText(context, "${nickname}님 안녕히가세요!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                        // 프래그먼트 종료
                        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                    }
                }
            }
        }

        // 단어장 추가 이동
        binding.vokanoteAddMoveButton.setOnClickListener() {
            val intent = Intent(context, VokanoteAddActivity::class.java)
            intent.putExtra("email", email)
            activityLauncher.launch(intent)
        }

        // 단어장 이동
        binding.vokanote1.setOnClickListener() {
            val intent = Intent(context, VokaAroundActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun openActivityResultLauncher(): ActivityResultLauncher<Intent> {
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val title = result.data?.getStringExtra("title")
                Log.d("log", "$title")
                Toast.makeText(context, "${title} 단어장 추가 성공", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "단어장 추가 실패", Toast.LENGTH_SHORT).show()
            }
        }
        return resultLauncher
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}