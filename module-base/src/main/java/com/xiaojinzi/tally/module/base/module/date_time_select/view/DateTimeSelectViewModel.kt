package com.xiaojinzi.tally.module.base.module.date_time_select.view

import com.xiaojinzi.tally.module.base.module.date_time_select.domain.DateTimeSelectUseCase
import com.xiaojinzi.tally.module.base.module.date_time_select.domain.DateTimeSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class DateTimeSelectViewModel(
    private val useCase: DateTimeSelectUseCase = DateTimeSelectUseCaseImpl(),
): BaseViewModel(),
    DateTimeSelectUseCase by useCase{
}