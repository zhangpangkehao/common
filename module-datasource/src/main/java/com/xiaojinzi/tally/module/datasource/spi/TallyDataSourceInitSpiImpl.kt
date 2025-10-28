package com.xiaojinzi.tally.module.datasource.spi

import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceInitSpi
import com.xiaojinzi.tally.module.datasource.storage.db.tally.TallyDb
import kotlinx.coroutines.flow.Flow

@ServiceAnno(TallyDataSourceInitSpi::class)
class TallyDataSourceInitSpiImpl : TallyDataSourceInitSpi {

    override val isInitStateOb = MutableSharedStateFlow(
        initValue = false,
    )

    override suspend fun initTallyDataBase(
        userId: String,
    ) {
        TallyDb.initDataBase(
            userId = userId,
        )
        isInitStateOb.emit(
            value = true,
        )
    }

    override suspend fun destroyTallyDataBase() {
        TallyDb.destroyTallyDataBase()
        isInitStateOb.emit(
            value = false,
        )
    }

}