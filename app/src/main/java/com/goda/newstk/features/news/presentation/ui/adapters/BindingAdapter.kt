package com.goda.newstk.features.news.presentation.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.goda.newstk.R

object BindingAdapter {
    @BindingAdapter("normalizeDate")
    @JvmStatic
    fun normalizeDate(textView: TextView, date: String?) {
        if (date == null) return

        val splitted = date.dropLast(4).split("T")
        val normalizedTime = splitted[1]
        val normalizedDay = splitted[0].split("-").asReversed().joinToString("-")
        val normalizeDate = normalizedTime + "\n" + normalizedDay
        textView.text = normalizeDate
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, urlToImage: String?) {
        if (urlToImage.isNullOrBlank())
            imageView.visibility = View.GONE
        else
            Glide.with(imageView.context).load(urlToImage).into(imageView)
    }

    @BindingAdapter("underlinedText")
    @JvmStatic
    fun underlinedText(textView: TextView, text: String?) {
        if (text == null) return

        textView.tag = text
        val underlinedText = "<u>" + textView.context.getString(R.string.news_link) + "</u>"
        textView.text = HtmlCompat.fromHtml(underlinedText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    class ClickHandler {
        private var isDescriptionVisible = false

        fun onLinkClick(view: View) {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(view.tag.toString()))
            view.context.startActivity(i)
        }

        fun onTitleClick(view: View) {
            val transition = TransitionSet().apply {
                interpolator = FastOutLinearInInterpolator()
                addTransition(Fade())
                addTransition(ChangeBounds())
            }
            if (!isDescriptionVisible)
                TransitionManager.beginDelayedTransition(view.parent as ViewGroup, transition)
            isDescriptionVisible = !isDescriptionVisible
            (view.parent as ViewGroup).getChildAt(1).visibility = if (isDescriptionVisible) View.VISIBLE else View.GONE
        }
    }
}