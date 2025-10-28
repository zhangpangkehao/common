package com.xiaojinzi.tally.lib.res.model.network

import androidx.annotation.Keep
import com.xiaojinzi.tally.lib.res.model.exception.CommonBusinessException
import java.io.IOException

/**
 * 必须继承 IOException, 拦截器中最高只能抛出这个异常
 */
@Keep
data class AppNetworkException(
    val code: String,
    val requestUrl: String? = null,
    val responseCode: Int? = null,
    override val message: String? = null,
    override val cause: Throwable? = null,
) : IOException(
    message,
    cause,
) {

    companion object {
        // 返回格式错误
        const val CODE_RESPONSE_ERROR = "response_error"

        // 不支持这个操作
        const val CODE_NOT_SUPPORT = "not_support"

        // 用户 Toekn 验证失败
        const val CODE_USER_TOKEN_AUTH_FAIL = "user_token_auth_fail"

        // 账本不存在
        const val CODE_BOOK_IN_EXISTENCE = "book_in_existence"

        // 记录没找到, 重复删除也可能会有这个错误
        const val CODE_RECORD_NOT_FOUND = "record_not_found"

        // 存在未支付订单
        const val CODE_EXIST_UNPAID_ORDER = "exist_unpaid_order"

        // 账号已经绑定了微信
        const val CODE_ACCOUNT_ALREADY_BIND_WX = "account_already_bind_wx"

        // Vip 验证失败
        const val CODE_VIP_AUTH_FAIL = "vip_auth_fail"
    }

}

@Keep
data class AppRetryException(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : CommonBusinessException(
    message = message,
    cause = cause,
)
