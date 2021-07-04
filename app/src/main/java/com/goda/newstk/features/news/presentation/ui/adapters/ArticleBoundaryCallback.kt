package com.goda.newstk.features.news.presentation.ui.adapters

import android.util.Log
import androidx.paging.PagedList
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.features.news.data.repository.NewsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleBoundaryCallback(
    var request: BoundaryCallbackRequest,
    private val newsRepo: NewsRepo,
    private val scope: CoroutineScope
): PagedList.BoundaryCallback<Article>() {

    override fun onZeroItemsLoaded() {
        Log.e("test", "onZeroItemsLoaded: ")
        scope.launch(Dispatchers.IO) {
            newsRepo.loadNews(request.request)
            request.request = request.request.nextPage()
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        scope.launch(Dispatchers.IO) {
            newsRepo.loadNews(request.request)
            request.request = request.request.nextPage()
        }
    }
}