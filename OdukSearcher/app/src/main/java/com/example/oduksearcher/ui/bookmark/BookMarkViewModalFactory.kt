package com.example.oduksearcher.ui.bookmark

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.oduksearcher.data.repository.BookMarkRepository

class BookMarkViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BookMarkViewModel::class.java) -> BookMarkViewModel(
                BookMarkRepository(context.applicationContext)
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}