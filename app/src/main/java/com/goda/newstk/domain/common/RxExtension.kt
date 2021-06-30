package com.goda.newstk.domain.common

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <T> Single<T>.retryIfOffline(): Single<T> {
    return retry { err ->
        val retry: Boolean = (err is UnknownHostException)
        if (retry) Thread.sleep(3000)
        retry
    }
}

fun <T> Flowable<T>.mapNetworkErrors(): Flowable<T> {
    return singleOrError().onErrorResumeNext { error ->

        when (error) {
            is SocketTimeoutException ->
                Single.error(TimeoutException())
            is TimeoutException ->
                Single.error(TimeoutException())
            is UnknownHostException ->
                Single.error(UnknownHostException())
            is HttpException -> {
                val single: Single<T> = when {
                    error.code() == 401 -> {

                        Single.error(UnAuthorizedException(error.message()))
                    }
                    else -> {
                        Single.error(RequestErrorException(error.message()))

                    }
                }

                single
            }
            else -> Single.error(error)
        }
    }.toFlowable()
}

fun <T> Single<T>.mapNetworkErrors(): Single<T> {
    return onErrorResumeNext { error ->

        when (error) {
            is SocketTimeoutException ->
                Single.error(TimeoutException())
            is TimeoutException ->
                Single.error(TimeoutException())
            is UnknownHostException ->
                Single.error(UnknownHostException())
            is HttpException -> {
                val single: Single<T> = when {
                    error.code() == 401 -> {

                        Single.error(UnAuthorizedException(error.message()))
                    }

                    else -> {

                        Single.error(RequestErrorException(error.message()))

                    }
                }


                /* val errorServer: String? = JsonParser().parse(error.response()?.errorBody()?.string())
                  .asJsonObject["message"]
                  .asString*/
                single
            }
            else -> Single.error(error)
        }
    }
}


fun Completable.mapNetworkErrors(): Completable {
    return onErrorResumeNext { error ->
        when (error) {
            is HttpException -> {
                when {
                    error.code() == 401 -> Completable.error(UnAuthorizedException(error.message()))
                    else -> Completable.error(InternalServerErrorException())
                }
            }
            else -> Completable.error(error)
        }
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Completable.applyIoScheduler() = applyScheduler(Schedulers.io())

fun Completable.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun Completable.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())
/*
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is TimeoutException ->
                    ResultWrapper.NetworkError
                is UnknownHostException ->
                    ResultWrapper.NetworkError
                is HttpException -> {


                    when (val code = throwable.code()) {
                        401 -> {
                            val errorResponse = try {
                                throwable.response()?.errorBody()?.string()?.let {
                                    Gson()?.fromJson(
                                        it,
                                        ErrorServerWithMessage::class.java
                                    )
                                }
                            } catch (exception: Exception) {
                                null
                            }
                            ResultWrapper.GenericError(code, errorResponse)

                        }
                        else -> {
                            val errorResponse = try {
                                throwable.response()?.errorBody()?.string()?.let {


                                    Gson()?.fromJson(
                                        it,
                                        ErrorServer::class.java
                                    )
                                }
                            } catch (exception: Exception) {
                                null
                            }

                            if (errorResponse == null) {
                                ResultWrapper.GenericError(code, convertErrorBody(throwable))
                            } else
                                ResultWrapper.GenericErrorServer(code, errorResponse)

                        }
                    }

                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}*/

