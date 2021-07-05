package com.goda.newstk.features.news.presentation.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.goda.newstk.R
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.utils.NewsConstants.KEY_ARTICLE
import com.goda.newstk.utils.Utilities
import kotlinx.android.synthetic.main.fragment_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_news_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        article = intent.getParcelableExtra(KEY_ARTICLE)
        setupData()
    }

    private fun setupData() {
        collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        article?.let {
            collapsingToolbar.title = it.title ?: ""
            headline.text = it.title ?: ""
            description.text = it.description ?: ""
            source.text = it.source ?: ""
            if (it.publishedAt != null) publishDate.text = Utilities.formatDate(it.publishedAt)
            Glide.with(this)
                .asBitmap()
                .thumbnail(0.2f)
                .placeholder(R.drawable.images)
                .load(it.urlToImage)
                .into(image)
        }
    }
}