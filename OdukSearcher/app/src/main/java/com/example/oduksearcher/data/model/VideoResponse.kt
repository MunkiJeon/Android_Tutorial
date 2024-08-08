package com.example.oduksearcher.data.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("documents")
    val documents: List<VideoDocument>?,
    @SerializedName("meta")
    val meta: Meta?
)