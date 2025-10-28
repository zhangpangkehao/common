package com.xiaojinzi.tally.module.base.spi

import android.graphics.Bitmap
import android.graphics.Color
import androidx.annotation.ColorInt

interface QrCodeSpi {

    /**
     * 生成二维码
     */
    @Throws(Exception::class)
    suspend fun createQRCode(
        content: String,
        @ColorInt
        contentColor: Int = Color.BLACK,
        @ColorInt
        backgroundColor: Int = Color.WHITE,
        width: Int,
        height: Int,
    ): Bitmap

    /**
     * 扫描二维码
     */
    suspend fun scanQrCode(): String

}