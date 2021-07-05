package com.goda.newstk.data.datamodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.goda.newstk.data.localDb.Article

data class NewsResult(
    val articleData: LiveData<PagedList<Article>>,
    val networkErrors: LiveData<String>?
)