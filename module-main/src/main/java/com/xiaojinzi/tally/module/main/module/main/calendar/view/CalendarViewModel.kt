package com.xiaojinzi.tally.module.main.module.main.calendar.view

import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCase
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCaseImpl
import com.xiaojinzi.tally.module.main.module.main.calendar.domain.CalendarUseCase
import com.xiaojinzi.tally.module.main.module.main.calendar.domain.CalendarUseCaseImpl

@ViewLayer
class CalendarViewModel(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
    private val useCase: CalendarUseCase = CalendarUseCaseImpl(
        commonUseCase = commonUseCase,
    ),
    val commonBillListViewUseCase: CommonBillListViewUseCase = CommonBillListViewUseCaseImpl(
        commonUseCase = commonUseCase,
        billQueryConditionUseCase = useCase.billQueryConditionUseCase,
    ),
) : BaseViewModel(), CalendarUseCase by useCase {

    override fun onCleared() {
        super.onCleared()
        commonUseCase.destroy()
        useCase.destroy()
        commonBillListViewUseCase.destroy()
    }

}