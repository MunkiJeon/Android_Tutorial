package com.example.membershipform

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    // Activity Result에 콜백 등록 (MainActivity.kt)
    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            resultDataId = result
            et_signInId.setText(resultDataId!!.data!!.getStringExtra("ID") ?: throw Exception("아이디가 없습니다."))
            et_signInPw.setText("")
            userData.add(
                UserData(
                    resultDataId!!.data!!.getStringExtra("ID") ?: throw Exception("No ID"),
                    resultDataId!!.data!!.getStringExtra("PW") ?: throw Exception("No PW"),
                    resultDataId!!.data!!.getStringExtra("NAME") ?: throw Exception("No Name"),
                    resultDataId!!.data!!.getStringExtra("AGE") ?: throw Exception("No Age"),
                    resultDataId!!.data!!.getStringExtra("MBTI") ?: throw Exception("No MBTI"),
                    resultDataId!!.data!!.getStringExtra("GEN") ?: throw Exception("No Gender")
                )
            )
        }
    }
    private var resultDataId: ActivityResult? = null
    private lateinit var et_signInId: EditText
    private lateinit var et_signInPw: EditText

    private var userData: MutableList<UserData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        et_signInId = findViewById<EditText>(R.id.et_sinId)//아이디 입력 필드
        et_signInPw = findViewById<EditText>(R.id.et_sinPw)//비밀번호 입력 필드
        var btn_sinSignIn = findViewById<Button>(R.id.btn_sinSignIn)//로그인 버튼
        var btn_sinSignUp = findViewById<Button>(R.id.btn_sinSignUp)//회원가입 버튼

        /*튜터님께 여쭤볼 사항
        1. 로고 안뜨는 이유 -> src 를 srcCompat으로 바꾸니 됨 왜지?? + 랜치 달린것과 아닌것 차이
        2. registerForActivityResult
        3. 개인프로필 클래스 생성후 넘기기...
        */

        btn_sinSignIn.setOnClickListener {
            var loginCheck: Boolean = false
            val it_Home = Intent(this, HomeActivity::class.java)
            for (i in userData) {
                if (i.id == et_signInId.text.toString() && i.pw == et_signInPw.text.toString()) {
                    it_Home.putExtra("ID", i.id)
                    it_Home.putExtra("PW", i.pw)
                    it_Home.putExtra("NAME", i.name)
                    it_Home.putExtra("AGE", i.age)
                    it_Home.putExtra("GEN", i.gender)
                    it_Home.putExtra("MBTI", i.mbti)
                    loginCheck = true
                    break
                }
            }

            if (et_signInId.text.isNotEmpty() && et_signInPw.text.isNotEmpty() && loginCheck) {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
//                startActivity(it_Home)
            } else {
                Toast.makeText(this, "아이디/비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
        btn_sinSignUp.setOnClickListener {
            Log.d("디버깅_회원가입", "회원가입 누름")
            val it_SignUp = Intent(this, SignUpActivity::class.java)
            getResultText.launch(it_SignUp)
        }
    }
}