package com.goda.newstk.features.news.presentation.ui.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.goda.newstk.data.datamodel.Result
import com.goda.newstk.data.datamodel.parseErrorResponse
import com.goda.newstk.data.localDb.Article
import com.goda.newstk.features.news.data.repository.NewsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class ArticleBoundaryCallback(
    var request: BoundaryCallbackRequest,
    private val newsRepo: NewsRepo,
    private val scope: CoroutineScope
): PagedList.BoundaryCallback<Article>() {

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors
    override fun onZeroItemsLoaded() {
        Log.e("test", "onZeroItemsLoaded: ")
        scope.launch(Dispatchers.IO) {
      /*      when (val result = newsRepo.loadNews(request.request)) {
                is Result.Success<List<Article>> -> {
                    Log.d("Success ", "onZeroItemsLoaded: ")
                    request.request = request.request.nextPage()

                }
                is Result.Failure -> {
                    Log.d("Failure ", "onZeroItemsLoaded: ")

                    _networkErrors.postValue(result.t.message)


                }
            }*/
            try {
                when (val result = newsRepo.loadNews(request.request)) {
                    is Result.Success<List<Article>> -> {
                        Log.d("Success ", "onItemAtEndLoaded: ")
                        request.request = request.request.nextPage()

                    }
                       is Result.Failure -> {
                           Log.d("Failure ", "onItemAtEndLoaded: ")
                           _networkErrors.postValue(result.t.message)


                       }
                }

            } catch (e: Exception) {
                _networkErrors.postValue((e as HttpException).response()?.parseErrorResponse()?.message)

            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        scope.launch(Dispatchers.IO) {
            try {
                when (val result = newsRepo.loadNews(request.request)) {
                    is Result.Success<List<Article>> -> {
                        Log.d("Success ", "onItemAtEndLoaded: ")
                        request.request = request.request.nextPage()

                    }
                    is Result.Failure -> {
                        Log.d("Failure ", "onItemAtEndLoaded: ")
                        _networkErrors.postValue(result.t.message)


                    }
                }

            } catch (e: Exception) {
                _networkErrors.postValue((e as HttpException).response()?.parseErrorResponse()?.message)

            }
        }
    }
}