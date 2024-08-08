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
        initView()
    }

    private fun initView() = with(binding) {
        vpMain.adapter = ViewPagerAdapter(this@MainActivity)
        vpMain.isUserInputEnabled = false
        vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bnMain.menu.getItem(position).isChecked = true
            }
        })
        bnMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    vpMain.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.item_bookmark -> {
                    vpMain.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
}