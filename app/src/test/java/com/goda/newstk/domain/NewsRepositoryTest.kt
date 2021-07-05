package com.goda.newstk.domain

import com.goda.newstk.common.MockitoTest
import com.goda.newstk.common.whenever
import com.goda.newstk.data.localDb.ArticlesDao
import com.goda.newstk.features.news.data.remote.service.NewsApi
import com.goda.newstk.features.news.data.repository.NewsRepo
import com.goda.newstk.presentation.common.espresso_idling.ViewState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import retrofit2.Response

@RunWith(JUnit4::class)
class NewsRepositoryTest : MockitoTest() {

    @Mock
    lateinit var newsDao: ArticlesDao

    @Mock
    lateinit var newsSourceService: NewsApi

    @InjectMocks
    lateinit var newsRepository: NewsRepo

    @Test
    fun `get news articles from web when there is internet`() = runBlocking {
        // GIVEN
    /*    val fetchedArticles = listOf(
            NewsArticle(title = "Fetched 1", source = NewsArticle.Source()),
            NewsArticle(title = "Fetched 2", source = NewsArticle.Source())
        )
        val cachedArticles = listOf(
            NewsArticleDb(title = "Fetched 1", source = NewsArticleDb.Source()),
            NewsArticleDb(title = "Fetched 2", source = NewsArticleDb.Source())
        )
        val newsSource = NewsResponse(articles = fetchedArticles)
        val response = Response.success(newsSource)

        // WHEN
        whenever(newsSourceService.getTopHeadlines()) doReturn response
        whenever(newsDao.getNewsArticles()) doReturn flowOf(cachedArticles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
            ViewState.loading(),
            ViewState.success(cachedArticles)
        )*/
    }

    @Test
    fun `get cached news articles when there is no internet`() = runBlocking {
        // GIVEN
       /* val cachedArticles = listOf(NewsArticleDb(title = "Cached", source = NewsArticleDb.Source()))
        val error = RuntimeException("Unable to fetch from network")

        // WHEN
        whenever(newsSourceService.getArticlesEverything(emptyMap(),"")) doThrow error
        whenever(newsDao.getAll()) doReturn flowOf(cachedArticles)

        // THEN
        newsRepository.getNewsArticles().assertItems(
            ViewState.loading(),
            ViewState.success(cachedArticles)
        )*/
    }
}