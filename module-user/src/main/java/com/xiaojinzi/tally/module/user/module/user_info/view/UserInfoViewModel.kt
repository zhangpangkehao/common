package com.xiaojinzi.tally.module.user.module.user_info.view

import com.xiaojinzi.tally.module.user.module.user_info.domain.UserInfoUseCase
import com.xiaojinzi.tally.module.user.module.user_info.domain.UserInfoUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class UserInfoViewModel(
    private val useCase: UserInfoUseCase = UserInfoUseCaseImpl(),
): BaseViewModel(),
    UserInfoUseCase by useCase{
}