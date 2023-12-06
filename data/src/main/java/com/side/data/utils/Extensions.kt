package com.side.data.utils

import com.side.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

inline fun <T> Flow<T>.asResult(crossinline action: suspend (value: T) -> ResultType<T>): Flow<ResultType<T>> = this.transform {
    emit(action(it))
}.onStart {
    emit(ResultType.Loading)
}.catch {
    emit(ResultType.Error(it))
}

inline fun <T, R> Flow<T>.asResultOtherType(crossinline action: suspend (value: T) -> ResultType<R>): Flow<ResultType<R>> = this.transform {
    emit(action(it))
}.onStart {
    emit(ResultType.Loading)
}.catch {
    emit(ResultType.Error(it))
}