package com.xiaojinzi.tally.module.core.module.category_select1.view

import com.xiaojinzi.tally.module.core.module.category_select1.domain.CategorySelect1UseCase
import com.xiaojinzi.tally.module.core.module.category_select1.domain.CategorySelect1UseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class CategorySelect1ViewModel(
    private val useCase: CategorySelect1UseCase = CategorySelect1UseCaseImpl(),
): BaseViewModel(),
    CategorySelect1UseCase by useCase{
}