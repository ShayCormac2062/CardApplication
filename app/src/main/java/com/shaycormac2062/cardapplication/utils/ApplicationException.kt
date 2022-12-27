package com.shaycormac2062.cardapplication.utils

sealed class ApplicationException(override val message: String) : Exception(message) {

    data class ApiException(override val message: String = API_EXCEPTION)
        : ApplicationException(message)

    data class InternetException(override val message: String = INTERNET_CONNECTION_EXCEPTION)
        : ApplicationException(message)

    data class NotFoundCardNumberException(override val message: String = NOT_FOUND_CARD_NUMBER)
        : ApplicationException(message)

    companion object {
        const val INTERNET_CONNECTION_EXCEPTION = "You have problems with Internet. Please turn it on and try again."
        const val NOT_FOUND_CARD_NUMBER = "No card with this number. Please check your number and try again."
        const val API_EXCEPTION = "Sorry, problems on the server. Please try again later."
    }
}
