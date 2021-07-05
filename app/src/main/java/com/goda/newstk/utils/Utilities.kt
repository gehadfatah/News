package com.goda.newstk.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// Copy EditCopy text to the ClipBoard
fun copyToClipBoard(activity: Activity, string: String, msg: String) {
    val clipMan = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipMan.setPrimaryClip(ClipData.newPlainText("label", string))
    Toast.makeText(
        activity,
        msg,
        Toast.LENGTH_SHORT
    ).show()

}

object Utilities{

    fun formatDate(createdAt: String): String {
        val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        form.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val date = form.parse(createdAt)
            val cal = Calendar.getInstance()
            val tz = cal.timeZone
            form.timeZone = tz
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            simpleDateFormat.timeZone = tz //HH:mm
            if (date == null) return ""
            return simpleDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}