package com.example.oduksearcher.ui

sealed class ListItem {
    data class ImageItem(
        val uuid: String,
        val thumbnailUrl: String,
        val siteName: String,
        val datetime: String,
        var isBookmarked: Boolean = false
    ) : ListItem()

    data class VideoItem(
        val uuid: String,
        val thumbnail: String,
        val title: String,
        val datetime: String,
        var isBookmarked: Boolean = false
    ) : ListItem()

    data class LoadingItem(
        var isLoading: Boolean
    ) : ListItem()
}