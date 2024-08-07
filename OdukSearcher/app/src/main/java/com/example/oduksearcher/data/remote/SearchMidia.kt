package com.example.oduksearcher.data.remote

import com.example.oduksearcher.data.model.ImageResponse
import com.example.oduksearcher.data.model.VideoResponse
import retrofit2.http.*

interface SearchMedia {
    @GET("image")
    suspend fun getImageList(
        @Query("query") search: String, // 검색을 원하는 질의어
        @Query("sort") sort: String = "recency", // 결과 문서 정렬 방식 (recency: 최신순, accuracy (default): 정확도순)
        @Query("page") page: Int = 1, // 결과 페이지 번호, 1~50 사이, 기본값 1
        @Query("size") size: Int = 5 // 한 페이지에 보여질 문서 수, 1~80 사이, 기본값 80
    ): ImageResponse

    @GET("vclip")
    suspend fun getVideoList(
        @Query("query") search: String, // 검색을 원하는 질의어
        @Query("sort") sort: String = "recency", // 결과 문서 정렬 방식 (recency: 최신순, accuracy (default): 정확도순)
        @Query("page") page: Int = 1, // 결과 페이지 번호, 1~15 사이, 기본값 1
        @Query("size") size: Int = 5 // 한 페이지에 보여질 문서 수, 1~30 사이, 기본값 15
    ): VideoResponse
}