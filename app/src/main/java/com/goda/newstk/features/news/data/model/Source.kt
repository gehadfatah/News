package com.goda.newstk.features.news.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Source(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name: String = ""
) : Parcelable