package com.xiaojinzi.tally.module.core.module.category_sub_info.view

import com.xiaojinzi.tally.module.core.module.category_sub_info.domain.CategorySubInfoUseCase
import com.xiaojinzi.tally.module.core.module.category_sub_info.domain.CategorySubInfoUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class CategorySubInfoViewModel(
    private val useCase: CategorySubInfoUseCase = CategorySubInfoUseCaseImpl(),
): BaseViewModel(),
    CategorySubInfoUseCase by useCase{
}