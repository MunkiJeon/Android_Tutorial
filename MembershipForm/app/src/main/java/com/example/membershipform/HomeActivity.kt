package com.example.membershipform

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //싱글톤 혹은 쉐어드 프리페런스 사용

        var it_homeIdText = intent.getStringExtra("ID")//로그인창에서 불러온 ID
        var it_homeNameText = intent.getStringExtra("NAME")//로그인창에서 불러온 name

        var iv_homeProfilePic = findViewById<ImageView>(R.id.iv_homeProfilePic)
        var tv_homeId = findViewById<TextView>(R.id.tv_homeId)
        var tv_homeName = findViewById<TextView>(R.id.tv_homeName)
        var tv_homeAge = findViewById<TextView>(R.id.tv_homeAge)
        var tv_HomeGender = findViewById<TextView>(R.id.tv_HomeGender)
        var tv_homeMbti = findViewById<TextView>(R.id.tv_HomeMbti)

        tv_homeId.setText(tv_homeId.text.toString() + intent.getStringExtra("ID"))
        tv_homeName.setText(tv_homeName.text.toString() + intent.getStringExtra("NAME"))
        tv_homeAge.setText(tv_homeAge.text.toString() + intent.getStringExtra("AGE"))
        tv_HomeGender.setText(tv_HomeGender.text.toString() + intent.getStringExtra("GEN"))
        tv_homeMbti.setText(tv_homeMbti.text.toString() + intent.getStringExtra("MBTI"))

        when((0..9).random()){
            0 -> iv_homeProfilePic.setImageResource(R.drawable.coffee0)
            1 -> iv_homeProfilePic.setImageResource(R.drawable.coffee1)
            2 -> iv_homeProfilePic.setImageResource(R.drawable.coffee2)
            3 -> iv_homeProfilePic.setImageResource(R.drawable.coffee3)
            4 -> iv_homeProfilePic.setImageResource(R.drawable.coffee4)
            5 -> iv_homeProfilePic.setImageResource(R.drawable.coffee5)
            6 -> iv_homeProfilePic.setImageResource(R.drawable.coffee6)
            7 -> iv_homeProfilePic.setImageResource(R.drawable.coffee7)
            8 -> iv_homeProfilePic.setImageResource(R.drawable.coffee8)
            9 -> iv_homeProfilePic.setImageResource(R.drawable.coffee9)
        }

//        var btn_homeEnd = findViewById<Button>(R.id.btn_homeEnd)
        var lbtn_homeEnd = findViewById<ConstraintLayout>(R.id.lbtn_homeEnd)

//        btn_homeEnd.setOnClickListener{
//            Toast.makeText(this,"로그아웃 하였습니다!", Toast.LENGTH_SHORT).show()
//            finish()
////            val intent = Intent(this,SignInActivity::class.java )
////            startActivity(intent)
//        }
        lbtn_homeEnd.setOnClickListener{
            Toast.makeText(this,"로그아웃 하였습니다!", Toast.LENGTH_SHORT).show()
            finish()
//            val intent = Intent(this,SignInActivity::class.java )
//            startActivity(intent)
        }

    }
}