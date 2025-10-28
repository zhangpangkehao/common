package com.xiaojinzi.tally.module.core.module.account_info.view

import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.core.module.account_info.domain.AccountInfoUseCase
import com.xiaojinzi.tally.module.core.module.account_info.domain.AccountInfoUseCaseImpl

@ViewLayer
class AccountInfoViewModel(
    private val useCase: AccountInfoUseCase = AccountInfoUseCaseImpl(),
) : BaseViewModel(),
    AccountInfoUseCase by useCase