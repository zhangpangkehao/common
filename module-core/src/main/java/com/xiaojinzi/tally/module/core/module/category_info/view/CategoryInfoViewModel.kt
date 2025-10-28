package com.xiaojinzi.tally.module.core.module.category_info.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.module.core.module.category_info.domain.CategoryInfoUseCase
import com.xiaojinzi.tally.module.core.module.category_info.domain.CategoryInfoUseCaseImpl
import kotlinx.coroutines.flow.map

@ViewLayer
class CategoryInfoViewModel(
    private val useCase: CategoryInfoUseCase = CategoryInfoUseCaseImpl(),
) : BaseViewModel(),
    CategoryInfoUseCase by useCase