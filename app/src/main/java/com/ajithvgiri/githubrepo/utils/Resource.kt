package com.ajithvgiri.githubrepo.utils

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val error: NetworkError?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(msg: String, data: T? = null, error: NetworkError? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                error
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null,
                null
            )
        }
    }
}
