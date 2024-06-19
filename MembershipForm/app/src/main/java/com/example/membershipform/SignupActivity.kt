package com.example.membershipform

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.jar.Attributes.Name

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var iv_profilePic   = findViewById<ImageView>(R.id.iv_profilePic)//프로필 사진
        var et_supName      = findViewById<EditText>(R.id.et_supName)//이름 입력 필드
        var et_supId        = findViewById<EditText>(R.id.et_supId)//아이디 입력 필드
        var et_supPw        = findViewById<EditText>(R.id.et_supPw)//비밀번호 입력 필드
        var btn_supSignUp   = findViewById<Button>(R.id.btn_supSignUp)//회원가입 버튼

        btn_supSignUp.setOnClickListener{
            if (et_supName.text.toString() != "" && et_supId.text.toString() != "" && et_supPw.text.toString() != ""  ) {
                Log.d("디버깅_회원가입_if","NAME:${et_supName.text} ID:${et_supId.text} PW: ${et_supPw.text}")
                Toast.makeText(this,"${et_supName.text}님, 환영합니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignUpActivity,SignInActivity::class.java )
                startActivity(intent)
            }else{
                Log.d("디버깅_회원가입_else","NAME:${et_supName.text} ID:${et_supId.text} PW: ${et_supPw.text}")
                Toast.makeText(this,"입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}