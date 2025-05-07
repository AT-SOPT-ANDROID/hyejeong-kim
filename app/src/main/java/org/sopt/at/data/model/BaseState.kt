package org.sopt.at.data.model

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

sealed class BaseState<out T> {
    object Idle: BaseState<Nothing>()
    data class Success<out T>(val data: T) : BaseState<T>()
    data class Error(val message: String, val code: String) : BaseState<Nothing>()
}

suspend fun <T> runRemote(block: suspend () -> Response<T>): Flow<BaseState<T>> = flow {
    try {
        val response = block()
        if (response.isSuccessful) {
            response.body()?.let {
                BaseState.Success(it)
            } ?: run {
                BaseState.Error("응답이 비어 있습니다", "EMPTY")
            }
        } else {
            val error = Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
            BaseState.Error(error.message, error.code)
        }
    } catch (e: Exception) {
        Log.d(TAG, e.message.toString())
        BaseState.Error("네트워크 통신 에러", "EMPTY")
    }
}