package com.goda.newstk.features.news.data.remote.service

import com.goda.newstk.BuildConfig
import com.goda.newstk.features.news.data.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsApi {
    //q for Keywords or phrases to search for in the article title and body
    @GET("v2/everything?language=en&sortBy=publishedAt&from=2021-07-01&to=2021-07-01&q=tesla")
    suspend fun getArticlesEverything(
        @QueryMap options: Map<String, String?>,
        @Query("apiKey") apiKey: String = BuildConfig.api_key
    ): APIResponse

}