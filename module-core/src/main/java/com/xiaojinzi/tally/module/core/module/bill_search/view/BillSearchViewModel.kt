package com.xiaojinzi.tally.module.core.module.bill_search.view

import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCase
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCaseImpl
import com.xiaojinzi.tally.module.core.module.bill_search.domain.BillSearchUseCase
import com.xiaojinzi.tally.module.core.module.bill_search.domain.BillSearchUseCaseImpl

@ViewLayer
class BillSearchViewModel(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
    private val useCase: BillSearchUseCase = BillSearchUseCaseImpl(
        commonUseCase = commonUseCase,
    ),
    val commonBillListViewUseCase: CommonBillListViewUseCase = CommonBillListViewUseCaseImpl(
        commonUseCase = commonUseCase,
        billQueryConditionUseCase = useCase.billQueryConditionUseCase,
    ),
) : BaseViewModel(),
    BillSearchUseCase by useCase {
    override fun onCleared() {
        super.onCleared()
        commonUseCase.destroy()
        useCase.destroy()
        commonBillListViewUseCase.destroy()
    }

}