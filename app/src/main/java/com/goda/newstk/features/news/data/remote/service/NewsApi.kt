package com.goda.newstk.features.news.data.remote.service

import com.goda.newstk.BuildConfig
import com.goda.newstk.features.news.data.model.APIResponse
import com.goda.newstk.features.news.data.model.ArticlesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
const val LIMIT_PAGE_SIZE_LIST=20
interface NewsApi {
    //q for Keywords or phrases to search for in the article title and body
    @GET("v2/everything?language=en&sortBy=publishedAt&from=2021-07-01&to=2021-07-01&q=tesla")
     fun getArticlesEverything(
        @QueryMap options: Map<String, String?>,
        @Query("apiKey") apiKey: String

    ): Deferred<Response<APIResponse>>

    @GET("/everything?from=2021-07-01&to=2021-07-01&sortBy=popularity")
    fun getArticlesAsync(
        //q for Keywords or phrases to search for in the article title and body
        @Query("q")q: String ="android",
        @Query("apiKey")apiKey: String =BuildConfig.api_key,
        @Query(" language")language: String ="en",
        @Query("pageSize")pageSize: Int =LIMIT_PAGE_SIZE_LIST,
        @Query("page")page: Int = 1

    ): Deferred<Response<ArticlesResponse>>

}