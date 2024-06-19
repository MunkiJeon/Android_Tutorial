package com.example.membershipform

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        var tv_signInId = findViewById<TextView>(R.id.tv_singInId)//아이디 타이틀
//        var tv_signInPw = findViewById<TextView>(R.id.tv_singInPw)//비밀번호 타이틀
        var et_signInId     = findViewById<EditText>(R.id.et_sinId)//아이디 입력 필드
        var et_signInPw     = findViewById<EditText>(R.id.et_sinPw)//비밀번호 입력 필드
        var btn_sinSignIn   = findViewById<Button>(R.id.btn_sinSignIn)//로그인 버튼
        var btn_sinSignUp   = findViewById<Button>(R.id.btn_sinSignUp)//회원가입 버튼

        /*튜터님께 여쭤볼 사항
        1. 로고 안뜨는 이유 -> src 를 srcCompat으로 바꾸니 됨 왜지?? + 랜치 달린것과 아닌것 차이
        2.
        3.
        */
        btn_sinSignIn.setOnClickListener{
            if (et_signInId.text.toString() != "" && et_signInPw.text.toString() != "" ) {
                Log.d("디버깅_로그인_if","ID:${et_signInId.text} PW: ${et_signInPw.text}")
                Toast.makeText(this,"로그인 성공!",Toast.LENGTH_SHORT).show()
            }else{
                Log.d("디버깅_로그인","ID:${et_signInId.text} PW: ${et_signInPw.text}")
                Toast.makeText(this,"아이디/비밀번호를 확인해 주세요",Toast.LENGTH_SHORT).show()
            }
        }
        btn_sinSignUp.setOnClickListener{
            Log.d("디버깅_회원가입","회원가입 누름")
            val intent = Intent(this@SignInActivity,SignUpActivity::class.java )
            startActivity(intent)
        }
    }

}