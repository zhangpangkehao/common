package com.xiaojinzi.tally.module.main.module.setting.view

import com.xiaojinzi.tally.module.main.module.setting.domain.SettingUseCase
import com.xiaojinzi.tally.module.main.module.setting.domain.SettingUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class SettingViewModel(
    private val useCase: SettingUseCase = SettingUseCaseImpl(),
): BaseViewModel(), SettingUseCase by useCase