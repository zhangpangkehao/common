package com.xiaojinzi.tally.lib.res.model.network

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class AppResponseWrap<T>(
    val errorCode: String,
    val errorMessage: String?,
    val data: T,
) {

    companion object {
        const val ERROR_CODE_NAME = "errorCode"
        const val ERROR_MESSAGE_NAME = "errorMessage"
        const val DATA_NAME = "data"
        const val ERROR_CODE_SUCCESS_NAME = "success"
    }

    val requireData: T
        get() {
            return when (errorCode) {
                "0", "10000" -> data
                else -> {
                    throw AppNetworkException(
                        code = errorCode,
                        message = errorMessage,
                    )
                }
            }
        }

}

@Keep
@ModelForNetwork
data class AppEmptyResponse(
    val xxx: String? = null,
)

@JvmInline
@Keep
@ModelForNetwork
value class AppCallResultResponse(
    val value: Boolean,
) {

    init {
        require(value)
    }

}
