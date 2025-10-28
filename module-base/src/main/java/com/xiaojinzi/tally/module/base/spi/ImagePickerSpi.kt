package com.xiaojinzi.tally.module.base.spi

import android.content.Context
import android.net.Uri
import androidx.annotation.UiContext

interface ImagePickerSpi {

    data class ImagePickerResult(
        val uri: Uri,
        val fileName: String,
    )

    /**
     * 选择图片
     */
    suspend fun selectImage(
        @UiContext context: Context,
        count: Int = 1,
    ): List<ImagePickerResult>

}