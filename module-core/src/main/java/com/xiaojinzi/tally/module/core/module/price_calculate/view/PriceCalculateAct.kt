package com.xiaojinzi.tally.module.core.module.price_calculate.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_PRICE_CALCULATE,
)
@ViewLayer
class PriceCalculateAct : BaseBusinessAct<PriceCalculateViewModel>() {

    @AttrValueAutowiredAnno("flag")
    var flag: String? = null

    @AttrValueAutowiredAnno("value")
    var value: String = ""

    override fun getViewModelClass(): Class<PriceCalculateViewModel> {
        return PriceCalculateViewModel::class.java
    }

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        initOnceUseViewModel {
            requiredViewModel().costUseCase.costAppend(
                target = if ((value.toDoubleOrNull() ?: 0.0) == 0.0) {
                    ""
                } else {
                    value
                },
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    PriceCalculateViewWrap()
                }
            }
        }

    }

}