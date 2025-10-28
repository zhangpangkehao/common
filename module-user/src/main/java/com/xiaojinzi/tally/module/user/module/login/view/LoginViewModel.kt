package com.xiaojinzi.tally.module.user.module.login.view

import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.module.user.module.login.domain.LoginUseCase
import com.xiaojinzi.tally.module.user.module.login.domain.LoginUseCaseImpl

@ViewLayer
class LoginViewModel(
    private val useCase: LoginUseCase = LoginUseCaseImpl(),
) : BaseViewModel(),
    LoginUseCase by useCase {
}