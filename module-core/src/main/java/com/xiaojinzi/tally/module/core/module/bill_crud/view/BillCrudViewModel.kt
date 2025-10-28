package com.xiaojinzi.tally.module.core.module.bill_crud.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.module.core.module.bill_crud.domain.BillCrudUseCase
import com.xiaojinzi.tally.module.core.module.bill_crud.domain.BillCrudUseCaseImpl
import com.xiaojinzi.tally.module.core.module.category_select.view.CategorySelectViewModel
import kotlinx.coroutines.flow.map

@ViewLayer
class BillCrudViewModel(
    private val useCase: BillCrudUseCase = BillCrudUseCaseImpl(),
    val categorySelectViewModel: CategorySelectViewModel = CategorySelectViewModel(
        useCase = useCase.categorySelectUseCase,
    ),
) : BaseViewModel(),
    BillCrudUseCase by useCase {

    override fun onCleared() {
        super.onCleared()
        useCase.destroy()
        categorySelectViewModel.destroy()
    }

}