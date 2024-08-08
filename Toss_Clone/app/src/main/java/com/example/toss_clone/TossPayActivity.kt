package com.example.toss_clone

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class TossPayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_toss_pay)

        var iv_all_all = findViewById<ImageView>(R.id.iv_tosspay_all).apply {
            setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://pay.toss.im/pricing")
                    )
                )
            }
        }
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