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

    private val getResultText = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            resultDataId = result
            et_signInId.setText(resultDataId!!.data!!.getStringExtra("ID") ?:throw Exception("아이디가 없습니다."))
            et_signInPw.setText("")
        }
    }
    private var resultDataId : ActivityResult? = null
    lateinit var et_signInId : EditText
    lateinit var et_signInPw : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        var et_signInId = findViewById<EditText>(R.id.et_sinId)//아이디 입력 필드
        et_signInId = findViewById<EditText>(R.id.et_sinId)//아이디 입력 필드
        et_signInPw = findViewById<EditText>(R.id.et_sinPw)//비밀번호 입력 필드
        var btn_sinSignIn = findViewById<Button>(R.id.btn_sinSignIn)//로그인 버튼
        var btn_sinSignUp = findViewById<Button>(R.id.btn_sinSignUp)//회원가입 버튼

        /*튜터님께 여쭤볼 사항
        1. 로고 안뜨는 이유 -> src 를 srcCompat으로 바꾸니 됨 왜지?? + 랜치 달린것과 아닌것 차이
        2. registerForActivityResult
        3.
        */

        btn_sinSignIn.setOnClickListener {
            if (et_signInId.text.toString() != "" && et_signInPw.text.toString() != "") {
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                val it_Home = Intent(this, HomeActivity::class.java)
                it_Home.putExtra("ID", et_signInId.text.toString())
                startActivity(it_Home)
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

//    override fun onRestart() {
//        super.onRestart()
//        et_signInId.setText(resultDataId!!.data!!.getStringExtra("ID") ?:throw Exception("아이디가 없습니다."))//아이디 입력 필드
//
//    }

}