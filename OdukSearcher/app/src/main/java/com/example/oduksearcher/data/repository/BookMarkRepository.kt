package com.example.oduksearcher.data.repository

import android.content.Context
import com.example.oduksearcher.R
import com.example.oduksearcher.ui.ListItem
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

private const val BOOKMARK_LIST = "bookmark_list"

class BookMarkRepository(context: Context) {
    private val gson =
        GsonBuilder().registerTypeAdapter(ListItem::class.java, CustomDeserializer()).create()
    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.preference_file_key),
        Context.MODE_PRIVATE
    )

    private val _bookmarkList = loadBookmarkList()
    val bookmarkList get() = _bookmarkList.toList()

    fun addBookmark(item: ListItem) {
        if (!_bookmarkList.contains(item)) {
            _bookmarkList.add(0, item)
            saveBookmarkList()
        }
    }

    fun removeBookmark(item: ListItem) {
        _bookmarkList.remove(item)
        saveBookmarkList()
    }

    private fun loadBookmarkList(): MutableList<ListItem> {
        val jsonString = sharedPreferences.getString(BOOKMARK_LIST, null) ?: return mutableListOf()
        val listType = object : TypeToken<MutableList<ListItem>>() {}.type

        return gson.fromJson(jsonString, listType)
    }

    private fun saveBookmarkList() {
        val jsonString = gson.toJson(_bookmarkList)
        sharedPreferences.edit().putString(BOOKMARK_LIST, jsonString).apply()
    }
}