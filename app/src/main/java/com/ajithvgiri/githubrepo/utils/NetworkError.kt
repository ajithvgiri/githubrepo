package com.ajithvgiri.githubrepo.utils

class NetworkError(
    error: Error? = null,
    message: String,
    val httpError: Int,
    val errorCode: String? = null,
    val errorType: ErrorType = ErrorType.ERROR_UNKNOWN,
    val requestUrl: String? = null
) : Exception(message) {

    companion object {
        const val ERROR_NO_NETWORK = 99999
        const val ERROR_UNKNOWN = 9998
        const val ERROR_JSON_ENCODING = 9996
        const val ERROR_CANCELLED = 9995

        const val ERROR_EOF_EXCEPTION = "End of input"
    }

    fun getFriendlyMessage(): String {
        return "$message"
    }

    enum class ErrorType {
        ERROR_NO_NETWORK,
        ERROR_UNKNOWN,
        ERROR_API
    }
}