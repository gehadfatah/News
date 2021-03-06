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
import androidx.navigation.Navigation
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.goda.newstk.R
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.features.news.presentation.ui.fragments.NewsFragmentDirections

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
            Glide.with(imageView.context).load(urlToImage).placeholder(R.drawable.images).error(R.drawable.images).into(imageView)
    }

    @BindingAdapter("underlinedText")
    @JvmStatic
    public fun underlinedText(textView: TextView, text: String?) {
        if (text == null) return

        textView.tag = text
        val underlinedText = "<u>" + textView.context.getString(R.string.news_link) + "</u>"
        textView.text = HtmlCompat.fromHtml(underlinedText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    class ClickHandler {



        fun onTitleClick(view: View,article:Article) {

            Navigation.findNavController(view)
                .navigate(NewsFragmentDirections.actionNewsToDetail(article))

        }
    }
}