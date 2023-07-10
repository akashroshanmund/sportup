package com.akash.sportup.data.datasources

sealed class Results<T>(
    val data: T? = null,
    val ErrorMessage: String? = null,
    val hasResult: Boolean = false
) {

    class Success<T>(data: T?, ErrorMessage: String? = null, hasResult: Boolean = true) : Results<T>(data, ErrorMessage, hasResult)

    class Error<T>(ErrorMessage: String?, data: T? = null, hasResult: Boolean = true) : Results<T>(data, ErrorMessage,hasResult)

    class Loading<T> : Results<T>()

}
