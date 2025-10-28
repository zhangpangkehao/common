package com.xiaojinzi.tally.module.base.support

import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.tally.lib.res.model.tally.TallyAccountDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

/**
 * Tally 数据库的订阅帮助类
 */
object TallyDataSourceHelper {

    @OptIn(ExperimentalCoroutinesApi::class)
    @StateHotObservable
    fun subscribeCurrentBookCategoryList(): Flow<List<TallyCategoryDto>> {
        return AppServices
            .tallyDataSourceSpi
            .selectedBookStateOb
            .flatMapLatest { bokSelected ->
                bokSelected?.let { bokSelected1 ->
                    AppServices
                        .tallyDataSourceSpi
                        .subscribeAllCategory(
                            bookIdList = listOf(bokSelected1.id),
                        )
                } ?: flowOf(value = emptyList())
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @StateHotObservable
    fun subscribeCurrentBookAccountList(): Flow<List<TallyAccountDto>> {
        return AppServices
            .tallyDataSourceSpi
            .selectedBookStateOb
            .flatMapLatest { bookInfo ->
                bookInfo?.let { bookInfo1 ->
                    AppServices
                        .tallyDataSourceSpi
                        .subscribeAllAccount(
                            bookId = bookInfo1.id,
                        )
                } ?: flowOf(value = emptyList())
            }
    }

}