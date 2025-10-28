package com.xiaojinzi.tally.module.core.module.account_icon_select.view

import com.xiaojinzi.tally.module.core.module.account_icon_select.domain.AccountIconSelectUseCase
import com.xiaojinzi.tally.module.core.module.account_icon_select.domain.AccountIconSelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AccountIconSelectViewModel(
    private val useCase: AccountIconSelectUseCase = AccountIconSelectUseCaseImpl(),
): BaseViewModel(),
    AccountIconSelectUseCase by useCase{
}