package com.xiaojinzi.tally.module.user.module.about_us.view

import com.xiaojinzi.tally.module.user.module.about_us.domain.AboutUsUseCase
import com.xiaojinzi.tally.module.user.module.about_us.domain.AboutUsUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AboutUsViewModel(
    private val useCase: AboutUsUseCase = AboutUsUseCaseImpl(),
): BaseViewModel(),
    AboutUsUseCase by useCase{
}