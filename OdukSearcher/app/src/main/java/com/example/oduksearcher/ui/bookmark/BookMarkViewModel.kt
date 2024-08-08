package com.example.oduksearcher.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oduksearcher.ui.ListItem
import com.example.oduksearcher.data.repository.BookMarkRepository

class BookMarkViewModel(private val repository: BookMarkRepository) : ViewModel() {
    private val _bookmarkList: MutableLiveData<List<ListItem>> = MutableLiveData()
    val bookmarkList: LiveData<List<ListItem>> get() = _bookmarkList

    init {
        loadBookmarkList()
    }

    private fun loadBookmarkList() {
        _bookmarkList.value = repository.bookmarkList
    }

    fun addBookmark(item: ListItem) {
        repository.addBookmark(item)
        loadBookmarkList()
    }

    fun removeBookmark(item: ListItem) {
        repository.removeBookmark(item)
        loadBookmarkList()
    }
}