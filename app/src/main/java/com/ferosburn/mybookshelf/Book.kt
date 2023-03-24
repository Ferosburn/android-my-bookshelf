package com.ferosburn.mybookshelf

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val description: String,
    val cover: Int
) : Parcelable
