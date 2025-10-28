package com.xiaojinzi.tally.module.base.module.center_menu_select.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.module.common.base.interceptor.AlphaInAnimInterceptor
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.lib.res.model.support.MenuItem
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.support.alphaOutWhenFinish
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.BASE_CENTER_MENU_SELECT,
    interceptors = [
        AlphaInAnimInterceptor::class,
    ],
)
@ViewLayer
class CenterMenuSelectAct : BaseBusinessAct<CenterMenuSelectViewModel>() {

    @AttrValueAutowiredAnno("data")
    var dataList: ArrayList<MenuItem>? = null

    override fun getViewModelClass(): Class<CenterMenuSelectViewModel> {
        return CenterMenuSelectViewModel::class.java
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
            requiredViewModel().dataListObservableDto.value = dataList ?: emptyList()
        }

        setContent {
            AppTheme {
                StateBar {
                    CenterMenuSelectViewWrap()
                }
            }
        }

    }

    override fun finish() {
        super.finish()
        this.alphaOutWhenFinish()
    }

}