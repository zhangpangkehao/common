package com.xiaojinzi.tally.module.core.module.account_select.view

import androidx.lifecycle.viewModelScope
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.ktx.orNull
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.core.module.account_select.domain.AccountSelectUseCase
import com.xiaojinzi.tally.module.core.module.account_select.domain.AccountSelectUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@ViewLayer
class AccountSelectViewModel(
    private val useCase: AccountSelectUseCase = AccountSelectUseCaseImpl(),
) : BaseViewModel(),
    AccountSelectUseCase by useCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allAccountStateVo = useCase.bookIdStateOb
        .flatMapLatest { bookId ->
            bookId.orNull()?.let { bookId1 ->
                AppServices
                    .tallyDataSourceSpi
                    .subscribeAllAccount(
                        bookId = bookId1,
                    )
            } ?: flowOf(value = emptyList())
        }
        .map { list ->
            list.map { item ->
                AccountSelectVo(
                    id = item.id,
                    iconRsd = AppServices.iconMappingSpi[item.iconName],
                    name = item.name.orEmpty(),
                    balanceCurrent = item.balanceCurrent.toYuan(),
                )
            }
        }
        .sharedStateIn(
            scope = viewModelScope,
        )

}