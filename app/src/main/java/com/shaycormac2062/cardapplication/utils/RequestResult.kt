package com.shaycormac2062.cardapplication.utils

sealed class RequestResult<T>(
    val data: T? = null,
    val exception: ApplicationException? = null
) {

    class Success<T>(data: T?) : RequestResult<T>(data)
    class Error<T>(exception: ApplicationException, data: T? = null) : RequestResult<T>(data, exception)

}
