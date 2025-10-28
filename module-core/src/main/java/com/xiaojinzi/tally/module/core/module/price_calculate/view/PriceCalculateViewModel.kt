package com.xiaojinzi.tally.module.core.module.price_calculate.view

import com.xiaojinzi.tally.module.core.module.price_calculate.domain.PriceCalculateUseCase
import com.xiaojinzi.tally.module.core.module.price_calculate.domain.PriceCalculateUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class PriceCalculateViewModel(
    private val useCase: PriceCalculateUseCase = PriceCalculateUseCaseImpl(),
): BaseViewModel(),
    PriceCalculateUseCase by useCase{
}