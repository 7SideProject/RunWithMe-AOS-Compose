package com.side.domain.utils

sealed interface ResultType<out T> {
    data class Success<T>(val data: T) : ResultType<T>
//    data class Fail<T>(val data: T) : ResultType<T>
    data class Error(val exception: Throwable) : ResultType<Nothing>
    object Loading : ResultType<Nothing>

    fun onSuccess(
        action: (value: T) -> Unit
    ): ResultType<T> {
        if(this is Success) action(this.data)
        return this
    }

    fun onError(
        action: (value: Throwable) -> Unit
    ) {
        if(this is Error) action(this.exception)
        return
    }

}