package com.xiaojinzi.tally.module.core.module.account_detail.view

import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.support.toLocalImageItemDto
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCase
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListViewUseCaseImpl
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.account_detail.domain.AccountDetailUseCase
import com.xiaojinzi.tally.module.core.module.account_detail.domain.AccountDetailUseCaseImpl
import kotlinx.coroutines.flow.map

@ViewLayer
class AccountDetailViewModel(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
    private val useCase: AccountDetailUseCase = AccountDetailUseCaseImpl(
        commonUseCase = commonUseCase,
    ),
    val commonBillListViewUseCase: CommonBillListViewUseCase = CommonBillListViewUseCaseImpl(
        commonUseCase = commonUseCase,
        billQueryConditionUseCase = useCase.commonBillQueryConditionUseCase,
    ),
) : BaseViewModel(), AccountDetailUseCase by useCase {

    @StateHotObservable
    val accountInfoStateObVo = useCase.accountInfoStateOb
        .map { accountInfo ->
            accountInfo?.let { accountInfo1 ->
                AccountDetailVo(
                    accountId = accountInfo1.id,
                    icon = AppServices.iconMappingSpi[accountInfo1.iconName]?.toLocalImageItemDto(),
                    name = accountInfo1.name?.toStringItemDto(),
                    balanceCurrent = accountInfo1.balanceCurrent.toYuan(),
                )
            }
        }

    override fun onCleared() {
        super.onCleared()
        commonUseCase.destroy()
        useCase.destroy()
        commonBillListViewUseCase.destroy()
    }

}