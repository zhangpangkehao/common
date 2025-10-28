package com.xiaojinzi.tally.module.imagepreview.module.image_preview.view

import com.xiaojinzi.tally.module.imagepreview.module.image_preview.domain.ImagePreviewUseCase
import com.xiaojinzi.tally.module.imagepreview.module.image_preview.domain.ImagePreviewUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class ImagePreviewViewModel(
    private val useCase: ImagePreviewUseCase = ImagePreviewUseCaseImpl(),
): BaseViewModel(),
    ImagePreviewUseCase by useCase{
}