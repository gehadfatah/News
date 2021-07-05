package com.goda.newstk.features.news.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedList.Config
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.features.news.data.model.ApiRequestEverything
import com.goda.newstk.features.news.data.model.Language
import com.goda.newstk.features.news.data.model.SortBy
import com.goda.newstk.features.news.data.repository.NewsRepo
import com.goda.newstk.features.news.presentation.ui.adapters.ArticleBoundaryCallback
import com.goda.newstk.features.news.presentation.ui.adapters.BoundaryCallbackRequest
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepo: NewsRepo) : ViewModel() {
    private val articlesMaxSize = 80
    private val articlesPageSize = 20

    val articles: LiveData<PagedList<Article>>

    private val callback: ArticleBoundaryCallback
    private val boundaryCallbackRequest: BoundaryCallbackRequest
    val networkError: LiveData<String>

    init {
        val config = Config.Builder().apply {
            setMaxSize(articlesMaxSize)
            setPageSize(articlesPageSize)
            setEnablePlaceholders(false)
            setPrefetchDistance(articlesPageSize / 2)
        }.build()

        val request =
            ApiRequestEverything(
                sortBy = SortBy.PublishedAt,
                language = Language.English,
                page = 1
            )
        boundaryCallbackRequest = BoundaryCallbackRequest(request)

        callback = ArticleBoundaryCallback(boundaryCallbackRequest, newsRepo, viewModelScope)
        networkError = callback.networkErrors

        //save first pagesize in room cache
        articles = LivePagedListBuilder(newsRepo.articlesFactory(), config)
            .setBoundaryCallback(callback)
            .build()
    }


    fun searchNews(query: String, sortBy: SortBy) {
        val request = ApiRequestEverything(
            language = Language.English,
            q = query,
            sortBy = sortBy
        )

        boundaryCallbackRequest.request = request
        updateNews()
    }


    fun updateNews() {
        boundaryCallbackRequest.request = boundaryCallbackRequest.request.firstPage()
        callback.onZeroItemsLoaded()
    }
}