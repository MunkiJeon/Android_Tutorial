package com.example.oduksearcher

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.oduksearcher.databinding.ActivityMainBinding
import com.example.oduksearcher.ui.BookMarkFragment
import com.example.oduksearcher.ui.HomeFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        replaceFragment(HomeFragment())
        homeNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_search -> {
                    replaceFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.item_bookmark -> {
                    replaceFragment(BookMarkFragment())
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fl_fragment, fragment)
            .commit()
    }
}