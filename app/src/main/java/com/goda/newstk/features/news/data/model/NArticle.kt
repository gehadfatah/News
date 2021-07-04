package com.goda.newstk.features.news.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class NArticle(
    @SerializedName("author")
    var author: String = "",
    @SerializedName("content")
    var content: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("source")
    var source: Source = Source(),
    @SerializedName("title")
    var title: String = "",
    @SerializedName("url")
    var url: String = "",
    @SerializedName("urlToImage")
    var urlToImage: String = ""
) : Parcelable