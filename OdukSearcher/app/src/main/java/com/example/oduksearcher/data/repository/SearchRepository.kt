package com.example.oduksearcher.data.repository

import com.example.oduksearcher.data.model.ImageResponse
import com.example.oduksearcher.data.model.VideoResponse

interface SearchRepository {
    suspend fun getImageList(searchText: String, page: Int): ImageResponse
    suspend fun getVideoList(searchText: String, page: Int): VideoResponse
}