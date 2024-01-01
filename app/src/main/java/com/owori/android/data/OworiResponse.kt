package com.owori.android.data

import java.net.URLDecoder

data class OworiResponse<out T>(val status: Status, val code: String?, val message: String?, val data: T?, val exception: Exception?) {

    enum class Status {
        SUCCESS,
        API_ERROR,
        NETWORK_ERROR,
        LOADING
    }

    companion object {
        fun <T> success(code: String, data: T?): OworiResponse<T> {
            return OworiResponse(Status.SUCCESS, code, "", data, null)
        }

        fun <T> error(code: String, message: String): OworiResponse<T> {
            return OworiResponse(Status.API_ERROR, URLDecoder.decode(code, "UTF-8"), URLDecoder.decode(message, "UTF-8"), null, null)
        }

        fun <T> error(exception: Exception?): OworiResponse<T> {
            return OworiResponse(Status.NETWORK_ERROR, null, null, null, exception)
        }

        fun <T> loading(): OworiResponse<T> {
            return OworiResponse(Status.LOADING, null, null, null, null)
        }
    }

    override fun toString(): String {
        return "OworiResponse(status=$status, code=$code, message=$message, data=$data, error=$exception)"
    }
}