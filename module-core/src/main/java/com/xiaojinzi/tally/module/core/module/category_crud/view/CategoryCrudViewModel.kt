package com.xiaojinzi.tally.module.core.module.category_crud.view

import com.xiaojinzi.tally.module.core.module.category_crud.domain.CategoryCrudUseCase
import com.xiaojinzi.tally.module.core.module.category_crud.domain.CategoryCrudUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class CategoryCrudViewModel(
    private val useCase: CategoryCrudUseCase = CategoryCrudUseCaseImpl(),
): BaseViewModel(),
    CategoryCrudUseCase by useCase{
}