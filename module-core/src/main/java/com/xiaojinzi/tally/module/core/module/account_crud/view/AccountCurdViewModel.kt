package com.xiaojinzi.tally.module.core.module.account_crud.view

import com.xiaojinzi.tally.module.core.module.account_crud.domain.AccountCrudUseCase
import com.xiaojinzi.tally.module.core.module.account_crud.domain.AccountCrudUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AccountCrudViewModel(
    private val useCase: AccountCrudUseCase = AccountCrudUseCaseImpl(),
): BaseViewModel(),
    AccountCrudUseCase by useCase{
}