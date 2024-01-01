package com.owori.android.core

import com.owori.android.data.OworiResponse
import retrofit2.Response

open class BaseUseCase {

    internal suspend fun <T> getResponse(response: Response<T>): OworiResponse<T> {
        return try {
            if (response.isSuccessful) {
                return OworiResponse.success("000000", response.body())
            } else {
                val code = response.headers()[response.code().toString()] ?: ""
                val message = response.headers()[response.message()] ?: ""
                OworiResponse.error(code, message)
            }
        } catch (e: Exception) {
            OworiResponse.error(e)
        }
    }
}