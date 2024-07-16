package com.example.toss_clone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val bankImg = arrayListOf(
        //통장 아이콘
        R.drawable.logo_toss_symbol_fill,
        R.drawable.ibk,
        R.drawable.all,
        R.drawable.kdb,
        R.drawable.kb,
        R.drawable.seida,
        //카드 이미지
        R.raw.shinhan2,
        R.raw.shinhan,
    )

    fun changeShape(img: ImageView, imgNum: Int) {
        Glide.with(this)//this = MainActivity
            //바꿀 이미지 리소스
            .load(bankImg.get(imgNum))
            //apply = 이미지 옵션
            .apply(RequestOptions.bitmapTransform(RoundedCorners(70)))
            //into = 어디다 그릴지
            .into(img)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var iv_bankicon01 = findViewById<ImageView>(R.id.iv_bankicon01)
        var iv_bankicon02 = findViewById<ImageView>(R.id.iv_bankicon02)
        var iv_bankicon03 = findViewById<ImageView>(R.id.iv_bankicon03)
        var iv_bankicon04 = findViewById<ImageView>(R.id.iv_bankicon04)
        var iv_bankicon05 = findViewById<ImageView>(R.id.iv_bankicon05)
        var iv_bankicon06 = findViewById<ImageView>(R.id.iv_bankicon06)

        var iv_outlayCard01 = findViewById<ImageView>(R.id.iv_outlayCard01)
        var iv_outlayCard02 = findViewById<ImageView>(R.id.iv_outlayCard02)
        changeShape(iv_bankicon01, 0)
        changeShape(iv_bankicon02, 1)
        changeShape(iv_bankicon03, 2)
        changeShape(iv_bankicon04, 3)
        changeShape(iv_bankicon05, 4)
        changeShape(iv_bankicon06, 5)

        changeShape(iv_outlayCard01, 6)
        changeShape(iv_outlayCard02, 7)



        var nav_navigation = findViewById<BottomNavigationView>(R.id.nav_navigation)
        nav_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    var intentNav = Intent(this, MainActivity::class.java)
                    startActivity(intentNav)
                    true
                }R.id.benefits -> {
                    var intentNav = Intent(this, BenefitsActivity::class.java)
                    startActivity(intentNav)
                    true
                }R.id.pay -> {
                    var intentNav = Intent(this, TossPayActivity::class.java)
                    startActivity(intentNav)
                    true
                }R.id.stock -> {
                    var intentNav = Intent(this, StockActivity::class.java)
                    startActivity(intentNav)
                    true
                }R.id.all -> {
                    var intentNav = Intent(this, AllActivity::class.java)
                    startActivity(intentNav)
                    true
                }
                else -> false
            }
        }
    }

}