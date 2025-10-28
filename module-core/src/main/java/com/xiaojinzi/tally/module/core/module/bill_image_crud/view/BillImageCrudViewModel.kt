package com.xiaojinzi.tally.module.core.module.bill_image_crud.view

import com.xiaojinzi.tally.module.core.module.bill_image_crud.domain.BillImageCrudUseCase
import com.xiaojinzi.tally.module.core.module.bill_image_crud.domain.BillImageCrudUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class BillImageCrudViewModel(
    private val useCase: BillImageCrudUseCase = BillImageCrudUseCaseImpl(),
): BaseViewModel(),
    BillImageCrudUseCase by useCase{
}