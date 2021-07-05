package com.goda.newstk.data.localDb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Article(
    val source: String?,
    val title: String,
    val description: String?,
    @PrimaryKey
    val url: String,
    val content: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String?
) : Parcelable