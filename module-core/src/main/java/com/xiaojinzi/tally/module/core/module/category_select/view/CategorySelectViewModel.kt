package com.xiaojinzi.tally.module.core.module.category_select.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.tally.module.core.module.category_select.domain.CategorySelectUseCase
import com.xiaojinzi.tally.module.core.module.category_select.domain.CategorySelectUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.module.core.module.bill_crud.view.toBillCrudCategoryVo
import kotlinx.coroutines.flow.map

@ViewLayer
class CategorySelectViewModel(
    private val useCase: CategorySelectUseCase = CategorySelectUseCaseImpl(),
): BaseViewModel(),
    CategorySelectUseCase by useCase{

    @StateHotObservable
    val categorySpendingMapStateObVo = useCase.categorySpendingMapStateOb
        .map { map ->
            map
                .map { entity ->
                    entity.key.toBillCrudCategoryVo() to entity.value.map {
                        it.toBillCrudCategoryVo()
                    }.sortedByDescending { it.sort }
                }
                .sortedByDescending { it.first.sort }
                .toMap()
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val categoryIncomeMapStateObVo = useCase.categoryIncomeMapStateOb
        .map { map ->
            map
                .map { entity ->
                    entity.key.toBillCrudCategoryVo() to entity.value.map {
                        it.toBillCrudCategoryVo()
                    }.sortedByDescending { it.sort }
                }
                .sortedByDescending { it.first.sort }
                .toMap()
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val spendingCategoryGroupHasSublistMapStateVo = categorySpendingMapStateObVo
        .map { map ->
            map
                .entries
                .associate { entity ->
                    entity.key.id to entity.value.isNotEmpty()
                }
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val incomeCategoryGroupHasSublistMapStateVo = categoryIncomeMapStateObVo
        .map { map ->
            map
                .entries
                .associate { entity ->
                    entity.key.id to entity.value.isNotEmpty()
                }
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

    @StateHotObservable
    val spendingCategoryGroupListStateObVo = categorySpendingMapStateObVo
        .map {
            it.keys.toList()
        }

    @StateHotObservable
    val incomeCategoryGroupListStateObVo = categoryIncomeMapStateObVo
        .map {
            it.keys.toList()
        }

    @StateHotObservable
    val categoryGroupSelectedStateObVo = useCase.categoryGroupSelectedStateOb
        .map {
            it?.toBillCrudCategoryVo()
        }

    @StateHotObservable
    val categoryItemSelectedStateObVo = useCase.categoryItemSelectedStateOb
        .map {
            it?.toBillCrudCategoryVo()
        }

}