package com.xiaojinzi.tally.module.base.spi

import com.xiaojinzi.support.annotation.StateHotObservable
import kotlinx.coroutines.flow.Flow

interface TallyDataSyncSpi {

    companion object {
        const val TAG = "TallyDataSyncSpi"
    }

    /**
     * 是否正在同步
     */
    @StateHotObservable
    val isSyncingStateOb: Flow<Boolean>

    /**
     * 设置同步开关
     */
    fun setSyncSwitch(
        enable: Boolean,
    )

    /**
     * 尝试同步
     * 在需要同步的时候, 进行同步一次
     * @param bookIdList 如果为 null, 那么就是同步所有的账本 否则就是同步传入的账本
     */
    fun trySync(
        bookIdList: List<String>? = null,
    )

    fun trySyncSingleBook(
        bookId: String,
    ) {
        trySync(listOf(bookId))
    }

}