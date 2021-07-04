package com.goda.newstk.features.news.data.model

import com.goda.newstk.data.localDb.Article


data class APIResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>
) {
    inner class ArticleResponse(
        val source: Source,
        val author: String?,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        val publishedAt: String,
        val content: String?
    )

    fun convert(): List<Article> = this.articles.map {
        Article(
            it.source.name,
            it.title,
            it.description,
            it.url,
            it.urlToImage,
            it.publishedAt
        )
    }
}