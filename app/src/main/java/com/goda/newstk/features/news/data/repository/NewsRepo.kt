package com.goda.newstk.features.news.data.repository

import com.goda.newstk.data.localDb.Article
import com.goda.newstk.data.localDb.ArticlesDao
import com.goda.newstk.features.news.data.model.ApiRequest
import com.goda.newstk.features.news.data.remote.service.NewsApi
import com.goda.newstk.presentation.common.apiCall

import kotlinx.coroutines.Dispatchers
import com.goda.newstk.presentation.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NewsRepo(private val api: NewsApi, private val dao: ArticlesDao) {
    suspend fun loadNews(request: ApiRequest): Result<List<Article>> {
        return apiCall {
            val articles =
                api.getArticlesEverything(request.toMap()).convert()

            if (request.page != 1)
                cacheArticles(articles)
            else
                cleanCacheArticles(articles)
            articles
        }
    }

    private suspend fun cacheArticles(articles: List<Article>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(articles)
        }
    }

    private suspend fun cleanCacheArticles(articles: List<Article>) {
        withContext(Dispatchers.IO) {
            dao.cleanInsertAll(articles)
        }
    }

    fun articlesFactory() = dao.factory()

    fun getAll(): Flow<List<Article>> = dao.getAll()

    fun getTitleContains(query: String): Flow<List<Article>> {
        val formattedQuery = "%" + query.trim().toLowerCase() + "%"
        return dao.getTitleContains(formattedQuery)
    }
}