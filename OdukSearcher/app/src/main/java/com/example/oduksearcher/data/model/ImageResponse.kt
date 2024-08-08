package com.example.oduksearcher.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("documents")
    val documents: List<ImageDocument>?,
    @SerializedName("meta")
    val meta: Meta?,
)