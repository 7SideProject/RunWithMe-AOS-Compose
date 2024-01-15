package com.side.runwithme.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.side.domain.base.BaseResponse
import retrofit2.HttpException


val gson = Gson()

fun <T> HttpException.getBaseResponse() : BaseResponse<T>? =
    this.response()?.errorBody()?.string().let {jsonString ->
        val type = object : TypeToken<BaseResponse<T>>() {}.type
        return gson.fromJson<BaseResponse<T>>(jsonString, type)
    }

fun <T> handleHttpException(
    httpException: HttpException,
    action: (value: BaseResponse<T>) -> Unit
) {
    try {
        val response = httpException.getBaseResponse<T>()
        if (response != null) {
            action(response)
        }
    } catch (e: Exception) {

    }
}