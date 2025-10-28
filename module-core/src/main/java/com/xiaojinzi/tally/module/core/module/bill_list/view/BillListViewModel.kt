package com.xiaojinzi.tally.module.core.module.bill_list.view

import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCase
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCaseImpl
import com.xiaojinzi.tally.module.core.module.bill_list.domain.BillListUseCase
import com.xiaojinzi.tally.module.core.module.bill_list.domain.BillListUseCaseImpl

@ViewLayer
class BillListViewModel(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
    private val useCase: BillListUseCase = BillListUseCaseImpl(
        commonUseCase = commonUseCase,
    ),
    val commonBillListViewUseCase: CommonBillListViewUseCase = CommonBillListViewUseCaseImpl(
        commonUseCase = commonUseCase,
        billQueryConditionUseCase = useCase.billQueryConditionUseCase,
    ),
) : BaseViewModel(), BillListUseCase by useCase {

    override fun onCleared() {
        super.onCleared()
        commonUseCase.destroy()
        useCase.destroy()
        commonBillListViewUseCase.destroy()
    }

}