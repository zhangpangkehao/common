package com.xiaojinzi.tally.module.imagepicker.spi

import android.content.Context
import androidx.core.net.toUri
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.support.ktx.resumeExceptionIgnoreException
import com.xiaojinzi.support.ktx.resumeIgnoreException
import com.xiaojinzi.tally.module.base.spi.ImagePickerSpi
import com.xiaojinzi.tally.module.imagepicker.CoilEngine
import kotlin.coroutines.suspendCoroutine


@ServiceAnno(ImagePickerSpi::class)
class ImagePickerSpiImpl : ImagePickerSpi {

    override suspend fun selectImage(
        context: Context,
        count: Int,
    ): List<ImagePickerSpi.ImagePickerResult> {
        return suspendCoroutine { cot ->
            PictureSelector.create(context)
                .openGallery(SelectMimeType.ofImage())
                .setImageEngine(CoilEngine)
                .setMaxSelectNum(count)
                .forResult(object : OnResultCallbackListener<LocalMedia> {
                    override fun onResult(result: ArrayList<LocalMedia>) {
                        cot.resumeIgnoreException(
                            value = result.map {
                                ImagePickerSpi.ImagePickerResult(
                                    uri = it.path.toUri(),
                                    fileName = it.fileName
                                )
                            }
                        )
                    }

                    override fun onCancel() {
                        cot.resumeExceptionIgnoreException(
                            exception = IllegalStateException("user cancel")
                        )
                    }
                })
        }
    }

}