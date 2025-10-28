package com.xiaojinzi.tally.module.datasource.spi

import com.xiaojinzi.component.anno.ServiceDecoratorAnno
import com.xiaojinzi.tally.lib.res.model.tally.TallyAccountDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyAccountInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyLabelDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyLabelInsertDto
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppServices

@ServiceDecoratorAnno(TallyDataSourceSpi::class)
class TallyDataSourceSpiDecorator(
    private val targetSpi: TallyDataSourceSpi,
) : TallyDataSourceSpi by targetSpi {

    override suspend fun insertCategory(
        target: TallyCategoryInsertDto,
        isNeedSync: Boolean
    ): String {
        return targetSpi.insertCategory(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun insertOrUpdateCategoryList(
        targetList: List<TallyCategoryInsertDto>,
        isNeedSync: Boolean
    ): List<String> {
        return targetSpi.insertOrUpdateCategoryList(
            targetList = targetList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySync(
                        bookIdList = targetList.map { it.bookId }
                    )
            }
        }
    }

    override suspend fun updateCategory(
        target: TallyCategoryDto,
        isNeedSync: Boolean,
    ) {
        targetSpi.updateCategory(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun updateCategoryList(
        targetList: List<TallyCategoryDto>,
        isNeedSync: Boolean
    ) {
        targetSpi.updateCategoryList(
            targetList = targetList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySync(
                        bookIdList = targetList.map { it.bookId }
                    )
            }
        }
    }

    override suspend fun insertOrUpdateAccount(
        target: TallyAccountInsertDto,
        isNeedSync: Boolean,
    ): String {
        return targetSpi.insertOrUpdateAccount(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun insertOrUpdateAccountList(
        targetList: List<TallyAccountInsertDto>,
        isNeedSync: Boolean,
    ): List<String> {
        return targetSpi.insertOrUpdateAccountList(
            targetList = targetList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySync(
                        bookIdList = targetList.map { it.bookId },
                    )
            }
        }
    }

    override suspend fun updateAccount(
        target: TallyAccountDto,
        isNeedSync: Boolean,
    ) {
        targetSpi.updateAccount(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun insertBill(
        target: TallyBillInsertDto,
        labelIdList: List<String>,
        imageUrlList: List<String>,
        isNeedSync: Boolean,
    ): String {
        return targetSpi.insertBill(
            target = target,
            labelIdList = labelIdList,
            imageUrlList = imageUrlList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun updateBill(
        target: TallyBillDto,
        labelIdList: List<String>,
        imageUrlList: List<String>,
        isNeedSync: Boolean,
    ) {
        targetSpi.updateBill(
            target = target,
            labelIdList = labelIdList,
            imageUrlList = imageUrlList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun insertOrUpdateBillList(
        targetList: List<TallyBillInsertDto>,
        isNeedSync: Boolean
    ): List<String> {
        return targetSpi.insertOrUpdateBillList(
            targetList = targetList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySync(
                        bookIdList = targetList.map { it.bookId },
                    )
            }
        }
    }

    override suspend fun insertLabel(
        target: TallyLabelInsertDto,
        isNeedSync: Boolean,
    ): String {
        return targetSpi.insertLabel(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun updateLabel(
        target: TallyLabelDto,
        isNeedSync: Boolean,
    ) {
        return targetSpi.updateLabel(
            target = target,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySyncSingleBook(
                        bookId = target.bookId,
                    )
            }
        }
    }

    override suspend fun insertOrUpdateLabelList(
        targetList: List<TallyLabelInsertDto>,
        isNeedSync: Boolean
    ): List<String> {
        return targetSpi.insertOrUpdateLabelList(
            targetList = targetList,
            isNeedSync = false,
        ).apply {
            if (isNeedSync) {
                // 尝试开启同步
                AppServices
                    .tallyDataSyncSpi
                    ?.trySync(
                        bookIdList = targetList.map { it.bookId },
                    )
            }
        }
    }

}