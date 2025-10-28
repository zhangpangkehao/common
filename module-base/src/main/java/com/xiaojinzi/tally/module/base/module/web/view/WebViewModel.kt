package com.xiaojinzi.tally.module.base.module.web.view

import com.xiaojinzi.tally.module.base.module.web.domain.WebUseCase
import com.xiaojinzi.tally.module.base.module.web.domain.WebUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class WebViewModel(
    private val useCase: WebUseCase = WebUseCaseImpl(),
): BaseViewModel(),
    WebUseCase by useCase{
}