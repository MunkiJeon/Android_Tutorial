package com.example.oduksearcher.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.oduksearcher.R
import com.example.oduksearcher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // 화면 초기화
        initView()
    }

    private fun initView() = with(binding) {
        //뷰페이져에 어댑터 입히기(필수)
        mainVpContainer.adapter = ViewPagerAdapter(this@MainActivity)
        //스와이프로 화면넘김 유무(옵션)
        mainVpContainer.isUserInputEnabled = true
        //페이지 변환을 콜백해주는 펑션(필수)
        mainVpContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //네비게이션 바에 화면 선택 상태 통일화
                mainNavMenuNav.menu.getItem(position).isChecked = true
            }
        })
        //네비게이션 바의 버튼 터치시 뷰페이저 화면 번호 통일화
        mainNavMenuNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    mainVpContainer.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.item_bookmark -> {
                    mainVpContainer.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
}