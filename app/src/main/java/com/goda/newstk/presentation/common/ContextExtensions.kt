package com.goda.newstk.presentation.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


fun Context.showLongToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(msgId: Int) {
    Toast.makeText(this, getString(msgId), Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}


fun Context.showShortToast(msgId: Int) {
    Toast.makeText(this, getString(msgId), Toast.LENGTH_SHORT).show()
}

fun Context.unwrap(): Activity {
    var context = this
    while (context !is Activity && context is ContextWrapper) {
        context = (this as ContextWrapper).baseContext
    }
    return context as Activity
}

fun Context.getDeviceMetrics(): DisplayMetrics {
    val metrics = DisplayMetrics()
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    display.getMetrics(metrics)
    return metrics
}

/*fun Context.locationJsonFileToObjects(): LocationsJson {
    val text = resources.openRawResource(R.raw.locations)
        .bufferedReader().use { it.readText() }
    return Gson().fromJson(
        text,
        LocationsJson::class.java
    )

}*/

fun Context.hideKeyboard(view: View?) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.showKeyboard() {
    val view = this.currentFocus
    val methodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //assert(view != null)
    if (view != null)
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Fragment.showMessage(error: Throwable) {
    activity?.run {
        showMessage(error)
    }
}

/*fun Activity.showMessage(error: Throwable) {
    when (error) {
        is InternalServerErrorException -> showShortToast(R.string.error_occurred)
        is TimeoutException, is SocketTimeoutException -> showShortToast(R.string.time_out_message)
        is UnknownHostException, is ConnectException -> showShortToast(R.string.no_connection)
        is UnAuthorizedException -> {
            *//* SessionManager(AppController.getContext()).clearLoginSession()
             startActivity(Intent(this, LoginActivity::class.java))
             showShortToast(R.string.session)*//*
        }
    }
}*/

fun RecyclerView.ViewHolder.getImageWithPngEx(image:String): String {
    return getImageWithPngExtension(image)
}
fun getImageWithPngExtension(image:String): String {
    if (  image.isEmpty() || image.lastIndexOf(".svg") == -1) return "https://friendycar.com/img/eg-flag.png"
    return image.substring(0,image.lastIndexOf(".svg"))+".png"
}



