package com.example.toss_clone

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.toss_clone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //  by lazy = 최초로 사용될 때 초기화를 하는 "지연 초기화"를 의미
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val homeFragment by lazy { HomeFragment() }
//    private val benefitsFragment by lazy { BenefitsFragment() }
//    private val tossPayFragment by lazy { TossPayFragment() }
//    private val stockFragment by lazy { StockFragment() }
//    private val allFragment by lazy { AllFragment() }

//        //통장 아이콘
//        R.drawable.logo_toss_symbol_fill,
//        R.drawable.ibk,
//        R.drawable.all,
//        R.drawable.kdb,
//        R.drawable.kb,
//        R.drawable.seida,
//        //카드 이미지
//        R.raw.shinhan2,
//        R.raw.shinhan,

    //    fun changeShape(img: ImageView, imgNum: Int) {
//        Glide.with(this)//this = MainActivity
//            //바꿀 이미지 리소스
//            .load(bankImg.get(imgNum))
//            //apply = 이미지 옵션
//            .apply(RequestOptions.bitmapTransform(RoundedCorners(70)))
//            //into = 어디다 그릴지
//            .into(img)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.navNavigation.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home -> {
                    changeFragment(homeFragment)
                    true
                }

//                R.id.benefits -> {
//                    var intentNav = Intent(this, BenefitsActivity::class.java)
//                    startActivity(intentNav)
//                    true
//                }
//
//                R.id.pay -> {
//                    var intentNav = Intent(this, TossPayActivity::class.java)
//                    startActivity(intentNav)
//                    true
//                }
//
//                R.id.stock -> {
//                    var intentNav = Intent(this, StockActivity::class.java)
//                    startActivity(intentNav)
//                    true
//                }
//
//                R.id.all -> {
//                    var intentNav = Intent(this, AllActivity::class.java)
//                    startActivity(intentNav)
//                    true
//                }

                else -> false
            }
        }
        changeFragment(homeFragment)
    }
    private fun changeFragment(frag : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fl_frame, frag)
            .commit()
    }
}