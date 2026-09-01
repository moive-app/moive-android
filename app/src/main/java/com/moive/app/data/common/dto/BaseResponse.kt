package com.moive.app.data.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val HTTP_OK = 200

@Serializable
data class BaseResponse<T>(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T?,
)

fun BaseResponse<*>.checkSuccess() {
    if (code != HTTP_OK) throw IllegalStateException("API request failed.")
}

fun <T> BaseResponse<T>.checkData(): T {
    checkSuccess()
    return data ?: throw IllegalStateException("Successful response but data was null.")
}
