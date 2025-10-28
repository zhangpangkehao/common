package com.xiaojinzi.tally.module.core.spi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.tally.module.base.spi.CoreSpi
import com.xiaojinzi.tally.module.core.module.account_info.view.AccountInfoViewWrap
import kotlinx.coroutines.InternalCoroutinesApi

@ServiceAnno(CoreSpi::class)
class CoreSpiImpl : CoreSpi {

    @OptIn(
        InternalCoroutinesApi::class, ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    )
    @Composable
    override fun AssetsViewShared() {
        AccountInfoViewWrap(
            isShowBackIcon = false,
        )
    }

}