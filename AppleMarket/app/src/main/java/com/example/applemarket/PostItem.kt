package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostItem(
    var picture: Int,
    var title: String,
    var address: String,
    var cost: Int,
    val seller: String,
    var comment : String,
    var chat : Int = 0,
    var like : Int = 0,
    var isLike : Boolean = false
):Parcelable
