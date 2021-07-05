package com.goda.newstk.data.datamodel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ErrorResponse(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("message")
    var message: String = "",
    @SerializedName("status")
    var status: String = ""
) : Parcelable