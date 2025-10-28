package com.xiaojinzi.tally.lib.res.model.support

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelForNetwork

@Keep
@ModelForNetwork
data class AliSTSTokenRes(
    val securityToken: String,
    val accessKeyId: String,
    val accessKeySecret: String,
    val expiration: String
)

@Keep
data class AliSTSTokenResDto(
    val securityToken: String,
    val accessKeyId: String,
    val accessKeySecret: String,
    val expiration: String
)

fun AliSTSTokenRes.toDto(): AliSTSTokenResDto {
    return AliSTSTokenResDto(
        securityToken = securityToken,
        accessKeyId = accessKeyId,
        accessKeySecret = accessKeySecret,
        expiration = expiration
    )
}
