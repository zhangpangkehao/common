package com.xiaojinzi.tally.lib.res.model.app_update

import android.os.Parcelable
import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.ModelDto
import com.xiaojinzi.support.annotation.ModelForNetwork
import kotlinx.parcelize.Parcelize

@Keep
@ModelForNetwork
data class AppUpdateRes(
    val versionName: String?,
    val versionCode: Long,
    val updateDesc: String?,
    val downloadUrl: String?,
    val isForce: Boolean,
)

@Keep
@Parcelize
@ModelDto
data class AppUpdateResDto(
    val versionName: String?,
    val versionCode: Long,
    val updateDesc: String?,
    val downloadUrl: String?,
    val isForce: Boolean,
) : Parcelable

fun AppUpdateRes.toDto() = AppUpdateResDto(
    versionName = versionName,
    versionCode = versionCode,
    updateDesc = updateDesc,
    downloadUrl = downloadUrl,
    isForce = isForce,
)