package com.goda.newstk.features.news.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import android.os.Parcelable
import com.goda.newstk.data.localDb.Article
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ArticlesResponse(
    @SerializedName("status")
    var status: String = "",
    @SerializedName("totalResults")
    var totalResults: Int = 0
) : Parcelable