package com.goda.newstk.data.datamodel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> apiCall(apiCall: suspend () -> T): Result<T> =
    withContext(Dispatchers.IO) {
        try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }
fun <T> Response<T>.parseErrorResponse(): ErrorResponse {
    val gson = Gson()
    val type = object : TypeToken<ErrorResponse>() {}.type
    return gson.fromJson(errorBody()?.charStream(), type)
}