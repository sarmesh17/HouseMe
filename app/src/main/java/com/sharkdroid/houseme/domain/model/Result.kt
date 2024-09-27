package com.sharkdroid.houseme.domain.model

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T?):Result<T>(data)
    class  Loading<T>:Result<T>()
    class Error<T>(message: String, data: T? = null):Result<T>(data, message)

}