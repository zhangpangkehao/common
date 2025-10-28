package com.xiaojinzi.tally.module.base.spi

import androidx.compose.runtime.Composable

interface CoreSpi {

    /**
     * 资产的视图
     */
    @Composable
    fun AssetsViewShared()

}