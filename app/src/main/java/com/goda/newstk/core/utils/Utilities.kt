package com.goda.newstk.core.utils

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast

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
fun onLinkClick(view: View) {
    val i = Intent(Intent.ACTION_VIEW, Uri.parse(view.tag.toString()))
    view.context.startActivity(i)
}