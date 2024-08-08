package com.example.oduksearcher.ui

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.oduksearcher.ui.bookmark.BookMarkFragment
import com.example.oduksearcher.ui.search.SearchFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = when (position) {
        0 -> SearchFragment()
        else -> BookMarkFragment()
    }
}