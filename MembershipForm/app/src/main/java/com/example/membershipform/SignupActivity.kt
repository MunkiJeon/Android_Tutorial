package com.example.membershipform

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
    private val numPick by lazy { findViewById<NumberPicker>(R.id.np_num) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nightModeFlags = //onCreate때 확인 가능함
            getResources().configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                numPick.textColor = Color.WHITE
            }

            Configuration.UI_MODE_NIGHT_NO -> {
                numPick.textColor = Color.WHITE
            }
        }
        numPick.minValue = 14
        numPick.maxValue = 90

        var et_supName = findViewById<EditText>(R.id.et_supName)//이름 입력 필드
        var rb_supGenMale = findViewById<RadioButton>(R.id.rb_supGenMale)//남성 선택
        var rb_supGenFemale = findViewById<RadioButton>(R.id.rb_supGenFemale)//여성 선택
        var tb_supMbtiEI = findViewById<ToggleButton>(R.id.tb_supMbtiEI)//MBTI E-I
        var tb_supMbtiSN = findViewById<ToggleButton>(R.id.tb_supMbtiSN)//MBTI S-N
        var tb_supMbtiTF = findViewById<ToggleButton>(R.id.tb_supMbtiTF)//MBTI T-F
        var tb_supMbtiJP = findViewById<ToggleButton>(R.id.tb_supMbtiJP)//MBTI J-P
        var strMbti = "ESTJ"
        var et_supId = findViewById<EditText>(R.id.et_supId)//아이디 입력 필드
        var et_supPw = findViewById<EditText>(R.id.et_supPw)//비밀번호 입력 필드
        var btn_supSignUp = findViewById<Button>(R.id.btn_supSignUp)//회원가입 버튼

        tb_supMbtiEI.setOnClickListener {
            if(tb_supMbtiEI.isChecked)strMbti = strMbti.replace("E","I")
            else strMbti = strMbti.replace("I","E")
            Log.d("MBTI","MBTI:${strMbti}")
        }
        tb_supMbtiSN.setOnClickListener {
            if(tb_supMbtiSN.isChecked)strMbti = strMbti.replace("S","N")
            else strMbti = strMbti.replace("N","S")
            Log.d("MBTI","MBTI:${strMbti}")
        }
        tb_supMbtiTF.setOnClickListener {
            if(tb_supMbtiTF.isChecked)strMbti = strMbti.replace("T","F")
            else strMbti = strMbti.replace("F","T")
            Log.d("MBTI","MBTI:${strMbti}")
        }
        tb_supMbtiJP.setOnClickListener {
            if(tb_supMbtiJP.isChecked)strMbti = strMbti.replace("J","P")
            else strMbti = strMbti.replace("P","J")
            Log.d("MBTI","MBTI:${strMbti}")
        }

        btn_supSignUp.setOnClickListener {

            var supRegex = et_supId.text.matches(Regex("^[\\w]{4,15}\$")) //(숫자, 문자 포함의 4~15자리 이내)
                && et_supPw.text.matches(Regex("^[\\w!@#$%^&*~`\\-_=+]{6,15}\$"))
                //(숫자, 문자, 특수문자 포함 6~15자리 이내)

            var supTure = (et_supName.text.isNotEmpty() && et_supId.text.isNotEmpty() && et_supPw.text.isNotEmpty()
                            && (rb_supGenMale.isChecked || rb_supGenFemale.isChecked ))
            if (supTure && supRegex) {
                Log.d(
                    "디버깅_회원가입_if",
                    "NAME:${et_supName.text} ID:${et_supId.text} PW: ${et_supPw.text}"
                )
                Toast.makeText(this, "${et_supName.text}님, 환영합니다!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, SignInActivity::class.java).apply {
                    putExtra("ID",  "${et_supId.text}")
                    putExtra("PW",  "${et_supPw.text}")
                    putExtra("NAME","${et_supName.text}")
                    putExtra("AGE", "${numPick.value}")
                    if (rb_supGenMale.isChecked) putExtra("GEN", "${rb_supGenMale.text}")
                    else putExtra("GEN", "${rb_supGenFemale.text}")
                    putExtra("MBTI", "$strMbti")
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else if(!supRegex){
                Toast.makeText(this, "입력 양식을 지켜주세요. \nID = 숫자, 문자 포함의 4~15자리 이내\nPW = 숫자, 문자, 특수문자 포함의 6~15자리 이내", Toast.LENGTH_SHORT).show()
            }else {
                Log.d(
                    "디버깅_회원가입_else",
                    "NAME:${et_supName.text} ID:${et_supId.text} PW: ${et_supPw.text}"
                )
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}